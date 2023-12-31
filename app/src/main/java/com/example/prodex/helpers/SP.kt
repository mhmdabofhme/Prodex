package com.example.prodex.helpers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.example.prodex.models.user.User


class SP(context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences("prodex" , MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = preferences.edit()

    fun saveText(Key: String?, value: String?) {
        editor.putString(Key, value).apply()
    }

    fun saveUser(user: User?) {
        editor.putString(Constants.USER, Gson().toJson(user)).apply()
    }

    fun getUser(): User? {
        return Gson().fromJson(preferences.getString(Constants.USER, "{}"), User::class.java)
    }

//    fun saveChild(user: Child?) {
//        editor.putString(Constants.CHILD, Gson().toJson(user)).apply()
//    }
//
//    fun getChild(): Child? {
//        return Gson().fromJson(preferences.getString(Constants.CHILD, "{}"), Child::class.java)
//    }

    fun saveNumber(Key: String?, value: Int) {
        editor.putInt(Key, value).apply()
    }

    fun saveBoolean(Key: String?, value: Boolean) {
        editor.putBoolean(Key, value).apply()
    }

    fun getText(Key: String, defaultValue: String?): String? {
        return preferences.getString(Key, defaultValue)
    }

    fun getNumber(Key: String?, defaultValue: Int): Int {
        return preferences.getInt(Key, defaultValue)
    }

    fun getBoolean(Key: String?, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(Key, defaultValue)
    }

    fun clear() {
        val lang = getText(Constants.LANG, "en")
        editor.clear()
//        editor.remove(Constants.PARENT)
//        editor.remove(Constants.CHILD)
//        editor.remove(Constants.INTRO)
        editor.commit()
        saveText(Constants.LANG, lang)

    }

    companion object {
        fun getInstance(context: Context): SP {
            return SP(context)
        }
    }

}