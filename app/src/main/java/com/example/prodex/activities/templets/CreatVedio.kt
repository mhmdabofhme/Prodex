package com.example.prodex.activities.templets


import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build

import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.arthenica.mobileffmpeg.Config
import com.arthenica.mobileffmpeg.Config.RETURN_CODE_CANCEL
import com.arthenica.mobileffmpeg.Config.RETURN_CODE_SUCCESS
import com.arthenica.mobileffmpeg.FFmpeg

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.lang.Integer.min


class CreatVedio
{



    suspend fun convertToVideo(
        context: Context,
        bitmapList: List<Bitmap?>,
        effectedBitmaps: List<Bitmap?>,
        nameFile: String,
        musicFilePath: String,
        durationInSeconds:Int,
        executed: (Boolean, String?) -> Unit
    ) {
        val outputDirCap = File(context.cacheDir, "temp_cap")
        val outputDirEff = File(context.cacheDir, "temp_effect")

        outputDirCap.mkdirs()
        outputDirEff.mkdirs()

        val outputFilePath = File(context.cacheDir, "output.mp4").absolutePath
        val file = File(outputFilePath)
        file.delete()

        Log.d("TAG", "convertToVideo: $outputFilePath")


        val frameRate = 20 // Number of frames per second

        val totalFrames = durationInSeconds * frameRate
        val framesPerImage = totalFrames / bitmapList.size

        runBlocking {
            launch(Dispatchers.Default) {
                var frameCount = 0
                var bitmapIndex = 0

                while (frameCount < totalFrames) {
                    val bitmap = bitmapList[bitmapIndex % bitmapList.size]
                    val outputFile = File(outputDirCap, "frame$frameCount.png")

                    try {
                        bitmap?.compress(Bitmap.CompressFormat.PNG, 80, outputFile.outputStream())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    frameCount++
                    if (frameCount % framesPerImage == 0) {
                        bitmapIndex++
                    }
                }
            }

            launch(Dispatchers.Default) {
                var frameCount = 0
                var bitmapIndex = 0

                while (frameCount < totalFrames) {
                    val bitmap = effectedBitmaps[bitmapIndex % effectedBitmaps.size]
                    val outputFile = File(outputDirEff, "frame$frameCount.png")

                    try {
                        bitmap?.compress(Bitmap.CompressFormat.PNG, 80, outputFile.outputStream())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    frameCount++
                    if (frameCount % framesPerImage == 0) {
                        bitmapIndex++
                    }
                }
            }
        }


        val mediaMetadataRetriever = MediaMetadataRetriever()
        mediaMetadataRetriever.setDataSource(getAudioFilePath(context,musicFilePath))
        val audioDuration = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong()
        mediaMetadataRetriever.release()

        val duration = audioDuration?.div(1000)?.toInt() ?: 0 // Duration of the audio file in seconds

        // Adjust the duration to match the desired durationInSeconds for the video
        val adjustedDuration = min(duration, durationInSeconds)

        // Trim the audio file using FFmpeg

        val trimmedAudioFilePath = File(context.cacheDir, "trimmed_audio.mp3").absolutePath


        val trimCommand = arrayOf(
            "-i", getAudioFilePath(context,musicFilePath),
            "-ss", "0",
            "-t", adjustedDuration.toString(),
            "-c", "copy",
            "-y", trimmedAudioFilePath
        )



        val trimResult = FFmpeg.execute(trimCommand)
        if (trimResult == RETURN_CODE_SUCCESS) {


            val command = arrayOf(

                "-r", frameRate.toString(),
                "-i", "${outputDirCap.absolutePath}/frame%d.png",
                "-i", "${outputDirEff.absolutePath}/frame%d.png",
                "-i",trimmedAudioFilePath,
                "-filter_complex", "[0:v][1:v]overlay",
                "-c:a", "libmp3lame",
                "-y",
                outputFilePath

            )


            Log.d("TAG", "convertToVideo: $outputFilePath")


            try {
                val returnCode = FFmpeg.execute(command)
                Config.enableLogCallback { message -> Log.d("FFmpeg", message.toString()) }
                if (returnCode == RETURN_CODE_SUCCESS) {
                    Log.d("TAG", "Command execution completed successfully.")
                    outputDirCap.deleteRecursively()
                    outputDirEff.deleteRecursively()
                    executed(true, outputFilePath)
                    saveVideoToStorage(context, outputFilePath)

                } else if (returnCode == RETURN_CODE_CANCEL) {
                    Log.d("TAG", "Command execution cancelled by user.")
                    outputDirCap.deleteRecursively()
                    outputDirEff.deleteRecursively()
                    executed(false, " execution cancelled by user")
                } else {
                    Log.d("TAG", "Command execution failed with returnCode=$returnCode.")
                    outputDirCap.deleteRecursively()
                    outputDirEff.deleteRecursively()
                    executed(false, "Command execution failed with returnCode=$returnCode.")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        }


    private fun saveVideoToStorage(context: Context, videoFilePath: String): Uri? {
        val videoFile = File(videoFilePath)
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)


        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, videoFile.name)
            put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_MOVIES)
            val contentResolver = context.contentResolver
            val uri = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)

            uri?.let { destinationUri ->
                val outputStream = contentResolver.openOutputStream(destinationUri)
                outputStream?.use { output ->
                    val inputStream = FileInputStream(videoFile)
                    inputStream.use { input ->
                        input.copyTo(output)
                    }
                }

                // Notify the MediaScanner about the new video file
                MediaScannerConnection.scanFile(
                    context,
                    arrayOf(destinationUri.path),
                    null,
                    null
                )

                // Return the Uri of the saved video file
                destinationUri
            }
        }
        else {
            val destFile = File(storageDir, videoFile.name)
            videoFile.copyTo(destFile, overwrite = true)

            // Notify the MediaScanner about the new video file
            MediaScannerConnection.scanFile(
                context,
                arrayOf(destFile.absolutePath),
                null,
                null
            )

            // Return the Uri of the saved video file
            Uri.fromFile(destFile)
        }
    }

    private fun getAudioFilePath(context: Context, uri: String): String? {
        var filePath: String? = null
        val projection = arrayOf(MediaStore.Audio.Media.DATA)
        val cursor = context.contentResolver.query(Uri.parse(uri), projection, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
                filePath = it.getString(columnIndex)
            }
        }
        return filePath
    }
/*
suspend fun convertToVideo(
    context: Context,
    bitmaps: List<Bitmap>,
    audioFilePath: String
): String? = withContext(Dispatchers.Default) {
    val outputDirCap = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)?.absolutePath
    val outputDirEff = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)?.absolutePath

    val duration = 5 * 1000 // 5 seconds
    val frameRate = 30
    val totalFrames = duration / 1000 * frameRate
    val framesPerImage = totalFrames / bitmaps.size

    val captureFramesJob = launch {
        for ((index, bitmap) in bitmaps.withIndex()) {
            val framePath = "$outputDirCap/frame$index.png"
            saveBitmapAsPng(bitmap, framePath)

            for (i in 0 until framesPerImage) {
                val frameIndex = index * framesPerImage + i
                val framePath = "$outputDirCap/frame$frameIndex.png"
                saveBitmapAsPng(bitmap, framePath)
            }
        }
    }

    val effectFramesJob = launch {
        // Apply effects and save affected frames
    }

    captureFramesJob.join()
    effectFramesJob.join()

    val videoFilePath = "$outputDirEff/video.mp4"
    Log.d("TAG", "convertToVideo: $videoFilePath")









    val command = arrayOf(
        "-framerate", frameRate.toString(),
        "-i", "$outputDirEff/frame%d.png",
        "-i", audioFilePath,
        "-filter_complex", "[0:v][1:a]concat=n=1:v=1:a=1",
        "-c:v", "copy",
        "-c:a", "aac",
        "-strict", "experimental",
        "-map", "0:v:0",
        "-map", "1:a:0",
        "-y",
        videoFilePath
    )




    val returnCode = FFmpeg.execute(command)

    if (returnCode == RETURN_CODE_SUCCESS) {
        deleteDirectory(File(outputDirCap))
        val videoFile = File(videoFilePath)
        val videoUri = insertVideoToMediaStore(context, videoFile)
        return@withContext videoUri?.toString()
    } else {
        deleteDirectory(File(outputDirCap))
        deleteDirectory(File(outputDirEff))
        return@withContext null
    }
}

    private fun saveBitmapAsPng(bitmap: Bitmap, filePath: String) {
        FileOutputStream(filePath).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
    }

    private fun deleteDirectory(directory: File) {
        if (directory.isDirectory) {
            val children = directory.list()
            children?.forEach { child ->
                val file = File(directory, child)
                file.delete()
            }
        }
        directory.delete()
    }

    private fun insertVideoToMediaStore(context: Context, videoFile: File): Uri? {
        val contentValues = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, "video_${getTimestamp()}.mp4")
            put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
            put(MediaStore.Video.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
            put(MediaStore.Video.Media.DATE_MODIFIED, System.currentTimeMillis() / 1000)
            put(MediaStore.Video.Media.DATA, videoFile.absolutePath)
        }

        val contentResolver = context.contentResolver
        val uri = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)
        MediaScannerConnection.scanFile(context, arrayOf(videoFile.absolutePath), null, null)

        return uri
    }

    private fun getTimestamp(): String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        return sdf.format(Date())
    }




    //        val command2= arrayOf(
//            "-i", outputFilePath,
//            "-i", "/storage/emulated/0/Movies/Music.mp3",
//            "-filter_complex",
//            "[0:v][1:v]overlay[v];[v][1:a]concat=n=1:v=1:a=1[vout][aout]",
//            "-map", "[vout]", "-map", "[aout]",
//            "-c:v", "libx264", "-c:a", "aac", "-b:a", "192k",
//            "-pix_fmt", "yuv420p",
//            "-y", outputFilePath
        //)




 */
}


