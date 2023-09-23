package com.example.prodex.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.anzeh.customdialog.CustomConfirmDialog
import com.anzeh.customdialog.utils.ConfirmDialogCallBack
import com.example.prodex.R
import com.example.prodex.activities.templets.templetOne.CreatTemplet
import com.example.prodex.activities.templets.templetOne.ShowTempletOne
import com.example.prodex.databinding.ActivityProductDataBinding
import com.example.prodex.helpers.isPhone
import com.example.prodex.helpers.isValidEmail
import com.example.prodex.helpers.showSnackBar

class ProductDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!arePermissionsGranted()) {
            requestPermissions()
        }
        binding.imgBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
//            if (isValid()) {

            //show text to make shore that not making any action
            startActivity(Intent(baseContext, CreatTemplet::class.java))


//            }
        }

    }


    private fun isValid(): Boolean {
        if (binding.editProductName.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editReach.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editProductLink.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else if (binding.editAdditionalText.text.trim().isEmpty()) {
            binding.root.showSnackBar()
            return false
        } else {
            return true
        }
    }
    private val STORAGE_PERMISSION_REQUEST_CODE = 1

    // Check if the required permissions are granted
    private fun arePermissionsGranted(): Boolean {
        val readPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val writePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return readPermission == PackageManager.PERMISSION_GRANTED &&
                writePermission == PackageManager.PERMISSION_GRANTED
    }

    // Request the required permissions
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            STORAGE_PERMISSION_REQUEST_CODE
        )
    }

    // Handle the permission request result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // The required permissions have been granted
                // You can now perform operations that require read/write access to external storage
                Log.d("TAG", "onRequestPermissionsResult: YES")
            } else {
                // The required permissions have been denied
                // Handle this situation or inform the user accordingly
                Log.d("TAG", "onRequestPermissionsResult: NO")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Check if the required permissions are granted when the activity resumes

    }
}