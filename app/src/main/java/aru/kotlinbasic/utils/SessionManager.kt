package clipz.customer.utils


import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources

import android.text.TextUtils
import com.google.gson.Gson
import android.util.Log
import aru.kotlinbasic.R


class SessionManager(private val context: Context) {
    private val resources: Resources

    companion object {

        private lateinit var pref: SharedPreferences
        private lateinit var editor: SharedPreferences.Editor
        lateinit var instance: SessionManager

        // Shared pref mode
        private val PRIVATE_MODE = 0
        var REQUIRED_UPDATE_FAVOURITE_LIST = false
        var UPDATE_FAVOURITE_BABYSITTER = false
    }

    enum class FavouriteBabysitterType {
        NEAR_BY, FEATURED, HISTORY, ACCEPTED, REVIEW_UPDATED, NONE, DELETED
    }

    init {
        instance = this
        resources = context.resources
        pref = context.getSharedPreferences(resources.getString(R.string.app_shared_pref), PRIVATE_MODE)
        editor = pref.edit()
    }


    fun put(key: String, value: String): String {
        editor.putString(key, value)
        editor.commit()
        return key
    }

    fun put(key: String, value: Boolean): String {
        editor.putBoolean(key, value)
        editor.commit()
        return key
    }

    fun put(key: String, value: Int): String {
        editor.putInt(key, value)
        editor.commit()
        return key
    }

    operator fun get(key: String, value: String): String = pref.getString(key, value)

    operator fun get(key: String, value: Boolean): Boolean = pref.getBoolean(key, value)


    operator fun get(key: String, value: Int): Int = pref.getInt(key, value)


    fun logout() {
        editor.clear()
        editor.commit()
    }

   /* fun getString(key: String): String {
        return pref.getString(resources.getString(R.string.user_model),"")
    }

    fun storeUserDetails(userModel: UserModel) {
        val gson = Gson()
        val json = gson.toJson(userModel)
        setString(resources.getString(R.string.user_model), json)
    }

    fun getUserDetails(isSet: Boolean): UserModel? {
         var userInformation: UserModel? = null
        val gson = Gson()


        val json = getString(resources.getString(R.string.user_model))


        if (TextUtils.isEmpty(json))
            return null
        userInformation= gson.fromJson(json, UserModel::class.java)
        if (isSet)
            AppConstant.CURRENT_USER = userInformation

        return userInformation
    }*/

    fun setString(key: String, value: String) {
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }


}
