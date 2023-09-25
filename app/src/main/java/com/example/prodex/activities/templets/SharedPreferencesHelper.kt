package com.example.prodex.activities.templets

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class SharedPreferencesHelper
    (context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun saveFileAudio(nameFile:String){
        editor.putString("FileAudio", nameFile)
        editor.apply()

    }
    fun getFileAudio():String
    {
        //Log.d("TAG", "getFileAudio: $(sharedPreferences.getString(\"FileAudio\", \"\").toString())")
        return sharedPreferences.getString("FileAudio", "").toString()


    }
    fun saveName(nameFile:String){
        editor.putString("namefile", nameFile)
        editor.apply()

    }
    fun getName():String
    {

        return sharedPreferences.getString("namefile", "").toString()


    }
 fun saveBitmapArrayList(bitmapList: ArrayList<Bitmap>) {



        val encodedBitmapList = ArrayList<String>()
        for (bitmap in bitmapList) {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            val encodedBitmap = Base64.encodeToString(byteArray, Base64.DEFAULT)
            encodedBitmapList.add(encodedBitmap)
        }
        editor.putString("bitmapList", encodedBitmapList.toString())
        editor.apply()

    }

    fun getBitmapArrayList(): ArrayList<Bitmap> {
        val encodedBitmapListString = sharedPreferences.getString("bitmapList", "")
        val encodedBitmapList = encodedBitmapListString?.substring(1, encodedBitmapListString.length - 1)
            ?.split(", ") ?: emptyList()

        val bitmapList = ArrayList<Bitmap>()
        for (encodedBitmap in encodedBitmapList) {
            val byteArray = Base64.decode(encodedBitmap, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            bitmap?.let { bitmapList.add(it) }
        }
        return bitmapList
    }
    fun deletBitMap()
    {
        editor.remove("bitmapList")
        editor.apply()

    }
}