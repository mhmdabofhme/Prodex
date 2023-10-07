package com.example.prodex.activities

import android.R.attr.data
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.MediaController
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.prodex.R
import com.example.prodex.databinding.ActivityTemplatePreviewBinding
import com.example.prodex.helpers.Permissions
import com.example.prodex.helpers.checkStoragePermissions
import com.example.prodex.helpers.getRealPathFromURI
import com.example.prodex.helpers.requestStoragePermissions
import com.example.prodex.helpers.showSnackBar
import java.io.ByteArrayOutputStream
import java.io.File


class TemplatePreviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTemplatePreviewBinding
    private var imageFile: File? = null
    private var imagesList = arrayListOf<File?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemplatePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.videoView.apply {
            val mediaController = MediaController(this@TemplatePreviewActivity)
            mediaController.setAnchorView(this)
            setMediaController(mediaController)
            setVideoURI(
                Uri.parse(
                    "android.resource://" + packageName + "/" +
                            R.raw.output
                )
            )
            start()

        }

        binding.btnNext.setOnClickListener {
            binding.videoView.stopPlayback()
            getImages()
        }


        // Register ActivityResult handler
//        val requestPermissions =
//            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
//                // Handle permission requests results
//                // See the permission example in the Android platform samples: https://github.com/android/platform-samples
//                if (results.)
//            }
//
//        // Permission request logic
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
//            requestPermissions.launch(
//                arrayOf(
//                    READ_MEDIA_IMAGES,
//                    READ_MEDIA_VIDEO,
//                    READ_MEDIA_VISUAL_USER_SELECTED
//                )
//            )
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            requestPermissions.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO))
//        } else {
//            requestPermissions.launch(arrayOf(READ_EXTERNAL_STORAGE))
//        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Permissions.permissionStorageId -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getImages()
                }
            }
        }
    }

    private fun getImages() {
        if (checkStoragePermissions()) {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            changeImage.launch(intent)

        } else {
            requestStoragePermissions()
        }
    }

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    // if multiple images are selected
                    if (it.data?.clipData != null) {
                        var count = it.data?.clipData?.itemCount
                        for (i in 0 until count!!) {
                            var imageUri: Uri = it.data?.clipData?.getItemAt(i)!!.uri
                            // Convert it to file to share it in API
                            imageFile = if (imageUri.toString().contains("content", true)) {
                                getRealPathFromURI(imageUri)?.let { realPath -> File(realPath) }
                            } else {
                                File(imageUri!!.path ?: "")
                            }
                            imagesList.add(imageFile)
//                      iv_image.setImageURI(imageUri) Here you can assign your Image URI to the ImageViews
                        }

                    } else if (it.data?.data != null) {
                        // if single image is selected
                        var imageUri: Uri = it.data!!.data!!
                        // Convert it to file to share it in API
                        imageFile = if (imageUri.toString().contains("content", true)) {
                            getRealPathFromURI(imageUri)?.let { realPath -> File(realPath) }
                        } else {
                            File(imageUri!!.path ?: "")
                        }
                        imagesList.add(imageFile)

//                  iv_image.setImageURI(imageUri) Here you can assign the picked image uri to your imageview
                    }
                }

                Activity.RESULT_CANCELED -> binding.root.showSnackBar(getString(R.string.snackbar))

            }
        }


}