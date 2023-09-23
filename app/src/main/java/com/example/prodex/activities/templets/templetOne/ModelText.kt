package com.example.prodex.activities.templets.templetOne

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelText (var size:Int,var colore:Int,var text:String) : Parcelable