package com.androidfinal_hygor_costa

import android.content.Context
import android.content.SharedPreferences

/*
 * Created by Tony Davidson on November 05, 2022
 *
 * Note: All changes you make in a SharedPreferences editor are batched,
 * they are not copied back to the original SharedPreferences until you call commit() or apply()
 * commit is immediate and can cause blocking
 * apply runs in the background therefore it is non-blocking
 *
 * All modifications to the preferences must go through an Editor object so that the preference
 * values remain in a consistent state and control when they are committed to storage.
 * Objects that are returned from the various get methods must be treated as immutable by the App
*/

class LocalStorage (context: Context = TheApp.context) {

    // region Properties
    private val preferencesName = context.getString(R.string.app_name) // use the app name
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(preferencesName,
        Context.MODE_PRIVATE)
    // endregion

    // region Methods
    fun contains(KEY_NAME: String): Boolean{
        return sharedPreferences.contains(KEY_NAME)
    }

    @Suppress("UNUSED")
    fun removeValue(KEY_NAME: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }

    @Suppress("UNUSED")
    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    @Suppress("UNUSED")
    fun getAll(): Map<String, *>{
        return sharedPreferences.all
    }
    // endregion

    // region Set methods
    fun save(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    @Suppress("UNUSED")
    fun save(KEY_NAME: String, int: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(KEY_NAME, int)
        editor.apply()
    }

    @Suppress("UNUSED")
    fun save(KEY_NAME: String, long: Long) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putLong(KEY_NAME, long)
        editor.apply()
    }

    @Suppress("UNUSED")
    fun save(KEY_NAME: String, float: Float) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putFloat(KEY_NAME, float)
        editor.apply()
    }

    @Suppress("UNUSED")
    fun save(KEY_NAME: String, bool: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_NAME, bool)
        editor.apply()
    }

    @Suppress("UNUSED")
    fun save(KEY_NAME: String, set: Set<String>) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putStringSet(KEY_NAME, set)
        editor.apply()
    }
    // endregion

    // region Get methods
    fun getValueString(KEY_NAME: String): String? {
        return sharedPreferences.getString(KEY_NAME, null)
    }

    @Suppress("UNUSED")
    fun getValueInt(KEY_NAME: String, value: Int): Int {
        return sharedPreferences.getInt(KEY_NAME, value)
    }

    @Suppress("UNUSED")
    fun getValueLong(KEY_NAME: String, value: Long): Long {
        return sharedPreferences.getLong(KEY_NAME, value)
    }

    @Suppress("UNUSED")
    fun getValueFloat(KEY_NAME: String, value: Float): Float {
        return sharedPreferences.getFloat(KEY_NAME, value)
    }

    @Suppress("UNUSED")
    fun getValueBool(KEY_NAME: String, value: Boolean): Boolean {
        return sharedPreferences.getBoolean(KEY_NAME, value)
    }

    @Suppress("UNUSED")
    fun getValueStringSet(KEY_NAME: String, value: Set<String>): Set<String>? {
        return sharedPreferences.getStringSet(KEY_NAME, value)
    }
    // endregion

}

