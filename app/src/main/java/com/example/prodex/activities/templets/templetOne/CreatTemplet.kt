package com.example.prodex.activities.templets.templetOne

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.anzeh.customdialog.CustomConfirmDialog
import com.anzeh.customdialog.utils.ConfirmDialogCallBack
import com.example.prodex.R
import com.example.prodex.databinding.ActivityCreatTempletBinding
import com.google.gson.Gson
import java.io.File


class CreatTemplet : AppCompatActivity() {
    private lateinit var binding: ActivityCreatTempletBinding
    private lateinit var lsitTemplet:ModelTempletOne
    var fileaduiPath:String = ""
    private val PICK_AUDIO = 1
    var AudioUri: Uri? = null
    var select_Audio: TextView? = null



    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            AudioUri = data?.data
            Log.d("TAG", ": $AudioUri ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatTempletBinding.inflate(layoutInflater)
        setContentView(binding.root)
       //list text modle
        var textModleList = ArrayList<ModelText>()
        var listColorBack =ArrayList<Int>()
        var listImage=ArrayList<Int>()




        binding.fileaduio.setOnClickListener {

//            val audio = Intent()
//            audio.type = "audio/*"
//            audio.action = Intent.ACTION_OPEN_DOCUMENT
//            startActivityForResult(Intent.createChooser(audio, "Select Audio"), PICK_AUDIO)
            pickAudio()


        }

        binding.creatVideo.setOnClickListener {


            listColorBack.add(R.color.white)
            listColorBack.add(R.color.white)
            listColorBack.add(R.color.white)
            listColorBack.add(R.color.white)

            listImage.add(R.drawable.therd)
            listImage.add(R.drawable.onetemp)
            listImage.add(R.drawable.imageone)
            listImage.add(R.drawable.imagthree)


            if( binding.textOnePage.text.toString()!=null){
         textModleList.add(ModelText(40,Color.YELLOW, binding.textOnePage.text.toString()+""))
            }
            if( binding.textTwoPage.text.toString()!=null){
         textModleList.add(ModelText(40, Color.RED, binding.textTwoPage.text.toString()+""))
            }
            if( binding.textThreePage.text.toString()!=null){
         textModleList.add(ModelText(40,Color.BLUE, binding.textThreePage.text.toString()+""))
            }
            if( binding.textFourPage.text.toString()!=null){
         textModleList.add(ModelText(40,Color.GREEN, binding.textFourPage.text.toString()+""))
            }

            lsitTemplet=  ModelTempletOne(textModleList,listColorBack,listImage,"abdalla",
                AudioUri.toString()
            )


            CustomConfirmDialog
                .Builder()
                .setContext(this)                //set Title
                .setTitle("Read Carefully")
                //setSubTitle
                .setSubTitle("Don't make any action in Screen \nwhen show the sample on view ")
                //set Cancel Button Title
                .setButtonCancelText("CANCEL")
                //set Confirm Button Title
                .setButtonConfirmText("CONFIRM")
                //set if user can dismiss dilog true or false
                .setEnableToDismiss(true)
                //handle Clickes Buttons
                .setDialogButtonsCallBack(object : ConfirmDialogCallBack {
                    override fun handleClickConfirmButton() {
                        val gson = Gson()
                        val lsitTemplettDataObjectAsAString: String = gson.toJson(lsitTemplet)

                        val intent = Intent(this@CreatTemplet, ShowTempletOne::class.java)
                        intent.putExtra("MylsitTempletObjectAsString", lsitTemplettDataObjectAsAString)
                        startActivity(intent)



                        //open sample to make vedio

                    }

                    override fun handleClickCancelButton() {

                    }

                    override fun handleWhenDialogDismiss() {

                    }

                })
                //set Main Color
                .setMainColor(R.color.primary)
                //set Custom Color
                .setIcon(R.drawable.box)
                //set Corner Buttons Radius
                .setCornerButtonsRadius(40)
                //set Corner background Dialog
                .setCornerRadius(20)
                //setDialog Background
                .setDialogColorBackground(R.color.white)
                .build()
                .showConfirmDialog()

        }




    }

    private fun pickAudio(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //No Permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else {
            openGallery()
        }
    }
    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(galleryIntent)
    }

}