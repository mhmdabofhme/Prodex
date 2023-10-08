package com.example.prodex.helpers

import android.Manifest
import android.app.Activity
import android.app.Notification
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import java.security.Permission

class Permissions {
    companion object {
        const val permissionCameraId = 101
        const val permissionStorageId = 102
        const val permissionNotificationId = 99
    }

}

fun Context.checkCameraPermissions(): Boolean {
    if (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        return true
    }
    return false
}

fun Activity.requestCameraPermissions() {
    ActivityCompat.requestPermissions(
        this,
        arrayOf(
            Manifest.permission.CAMERA
        ),
        Permissions.permissionCameraId
    )
}

fun Context.checkNotificationPermissions(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }
    return false
}

fun Activity.requestNotificationPermissions() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.POST_NOTIFICATIONS
            ),
            Permissions.permissionNotificationId
        )
    }
}

fun isNotificationsEnabled(){
}

fun Context.checkStoragePermissions(): Boolean {
    if (Build.VERSION.SDK_INT >= 34) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_IMAGES,
            ) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_VIDEO,
            ) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
            ) == PackageManager.PERMISSION_GRANTED
        )
            return true
    } else if (Build.VERSION.SDK_INT >= 29) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_IMAGES,
            ) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_VIDEO,
            ) == PackageManager.PERMISSION_GRANTED
        )
            return true
    } else {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            ) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            ) == PackageManager.PERMISSION_GRANTED
        )
            return true
    }
    return false

}

fun Activity.requestStoragePermissions() {
    if (Build.VERSION.SDK_INT >= 34) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
            ),
            Permissions.permissionStorageId
        )
    } else if (Build.VERSION.SDK_INT >= 33) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
            ),
            Permissions.permissionStorageId
        )
    } else {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            Permissions.permissionStorageId
        )
    }
}
