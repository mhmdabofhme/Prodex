package com.example.prodex.activities.templets.templetOne

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class ModelTempletOne (var modelText:List <ModelText> , var listColorBackGround :List<Int>,var ListIamgeLogo:List<Int> ,var fileName:String ,var fileaduio:String  ) :
    Parcelable