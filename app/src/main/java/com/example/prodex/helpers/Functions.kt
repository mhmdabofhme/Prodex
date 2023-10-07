package com.example.prodex.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.telephony.PhoneNumberUtils
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.prodex.R
import com.google.android.material.snackbar.Snackbar
import com.kaopiz.kprogresshud.KProgressHUD
import java.io.ByteArrayOutputStream
import java.util.Locale


fun Context.initProgress(): KProgressHUD {
    return KProgressHUD.create(this)
        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        .setLabel(getString(R.string.one_moment))
        .setCancellable(true)
        .setAnimationSpeed(2)
        .setDimAmount(0.5f)
}

fun View.showSnackBar(message: String? = context.getString(R.string.snackbar)) {
    var snackBarMessage = context.getString(R.string.snackbar)
    message?.also { snackBarMessage = it }

    val snack: Snackbar =
        Snackbar.make(this, snackBarMessage, Snackbar.LENGTH_LONG)
    val view = snack.view
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
    snack.setBackgroundTint(ContextCompat.getColor(context, R.color.teal75))
    val tv = view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
    tv.setTextColor(ContextCompat.getColor(context, R.color.white))
    tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
    snack.show()
}

fun Context.getLang(): String? {
    return SP.getInstance(this).getText(Constants.LANG, "en")
}

fun Context.saveLang(lang: String) {
    SP.getInstance(this).saveText(Constants.LANG, lang)
}


fun Activity.changeLanguage(isActivity: Boolean) {
    val myLocale = Locale(
        SP.getInstance(this).getText(Constants.LANG, Locale.getDefault().language).toString()
    )
    val config: Configuration = resources.configuration
    config.setLocale(myLocale)
    resources.updateConfiguration(config, resources.displayMetrics)
    window.decorView.layoutDirection = config.layoutDirection
    createConfigurationContext(config)

    if (!isActivity) {
        this.startActivity(Intent(this, this.javaClass))
        this.finish()
    }
}


 fun Activity.getImageUri(inImage: Bitmap): Uri? {
    val bytes = ByteArrayOutputStream()
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(
        contentResolver,
        inImage,
        System.currentTimeMillis().toString(),
        null
    )
    return Uri.parse(path)
}

 fun Activity.getRealPathFromURI(uri: Uri?): String? {
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val cursor: Cursor? = contentResolver.query(uri!!, proj, null, null, null)
    cursor!!.moveToFirst()
    val idx: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA)
    return cursor.getString(idx)
}

fun Activity.getTargetAge(): ArrayList<String> {
    return arrayListOf<String>("12-17", "18-24", "25-34", "35-44", this.getString(R.string.older))
}

fun Activity.getCountries(): ArrayList<String> {
    return arrayListOf<String>(
        "Afghanistan",
        "Albania",
        "Algeria",
        "Andorra",
        "Angola",
        "Antigua and Barbuda",
        "Argentina",
        "Armenia",
        "Australia",
        "Austria",
        "Azerbaijan",
        "Bahamas",
        "Bahrain",
        "Bangladesh",
        "Barbados",
        "Belarus",
        "Belgium",
        "Belize",
        "Benin",
        "Bhutan",
        "Bolivia",
        "Bosnia and Herzegovina",
        "Botswana",
        "Brazil",
        "Brunei",
        "Bulgaria",
        "Burkina Faso",
        "Burundi",
        "Cabo Verde",
        "Cambodia",
        "Cameroon",
        "Canada",
        "Central African Republic",
        "Chad",
        "Chile",
        "China",
        "Colombia",
        "Comoros",
        "Congo, Democratic Republic of the",
        "Congo, Republic of the",
        "Costa Rica",
        "Cote dIvoire",
        "Croatia",
        "Cuba",
        "Cyprus",
        "Czech Republic",
        "Denmark",
        "Djibouti",
        "Dominica",
        "Dominican Republic",
        "Ecuador",
        "Egypt",
        "El Salvador",
        "Equatorial Guinea",
        "Eritrea",
        "Estonia",
        "Eswatini",
        "Ethiopia",
        "Fiji",
        "Finland",
        "France",
        "Gabon",
        "Gambia",
        "Georgia",
        "Germany",
        "Ghana",
        "Greece",
        "Grenada",
        "Guatemala",
        "Guinea",
        "Guinea-Bissau",
        "Guyana",
        "Haiti",
        "Honduras",
        "Hungary",
        "Iceland",
        "India",
        "Indonesia",
        "Iran",
        "Iraq",
        "Ireland",
        "Israel",
        "Italy",
        "Jamaica",
        "Japan",
        "Jordan",
        "Kazakhstan",
        "Kenya",
        "Kiribati",
        "Korea, North",
        "Korea, South",
        "Kosovo",
        "Kuwait",
        "Kyrgyzstan",
        "Laos",
        "Latvia",
        "Lebanon",
        "Lesotho",
        "Liberia",
        "Libya",
        "Liechtenstein",
        "Lithuania",
        "Luxembourg",
        "Madagascar",
        "Malawi",
        "Malaysia",
        "Maldives",
        "Mali",
        "Malta",
        "Marshall Islands",
        "Mauritania",
        "Mauritius",
        "Mexico",
        "Micronesia",
        "Moldova",
        "Monaco",
        "Mongolia",
        "Montenegro",
        "Morocco",
        "Mozambique",
        "Myanmar",
        "Namibia",
        "Nauru",
        "Nepal",
        "Netherlands",
        "New Zealand",
        "Nicaragua",
        "Niger",
        "Nigeria",
        "North Macedonia",
        "Norway",
        "Oman",
        "Pakistan",
        "Palau",
        "Palestine",
        "Panama",
        "Papua New Guinea",
        "Paraguay",
        "Peru",
        "Philippines",
        "Poland",
        "Portugal",
        "Qatar",
        "Romania",
        "Russia",
        "Rwanda",
        "Saint Kitts and Nevis",
        "Saint Lucia",
        "Saint Vincent and the Grenadines",
        "Samoa",
        "San Marino",
        "Sao Tome and Principe",
        "Saudi Arabia",
        "Senegal",
        "Serbia",
        "Seychelles",
        "Sierra Leone",
        "Singapore",
        "Slovakia",
        "Slovenia",
        "Solomon Islands",
        "Somalia",
        "South Africa",
        "South Sudan",
        "Spain",
        "Sri Lanka",
        "Sudan",
        "Suriname",
        "Sweden",
        "Switzerland",
        "Syria",
        "Taiwan",
        "Tajikistan",
        "Tanzania",
        "Thailand",
        "Timor-Leste",
        "Togo",
        "Tonga",
        "Trinidad and Tobago",
        "Tunisia",
        "Turkey",
        "Turkmenistan",
        "Tuvalu",
        "Uganda",
        "Ukraine",
        "United Arab Emirates",
        "United Kingdom",
        "United States",
        "Uruguay",
        "Uzbekistan",
        "Vanuatu",
        "Vatican City",
        "Venezuela",
        "Vietnam",
        "Yemen",
        "Zambia",
        "Zimbabwe",
    )
}


fun String.isValidEmail(): Boolean {
    val regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$".toRegex()
    return this.matches(regex)
}

fun EditText.isPhone(): Boolean {
    var isPhone = true
    val number = this.text.toString()
    if ((number.length < 10) or (number.length > 13)) {
        isPhone = false
    }
    if (!PhoneNumberUtils.isGlobalPhoneNumber(number)) {
        isPhone = false
    }
    return isPhone
}