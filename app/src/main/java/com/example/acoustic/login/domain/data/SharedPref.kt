package com.example.acoustic.login.domain.data

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPref @Inject constructor(@ApplicationContext context: Context) {

    private  val prefs:SharedPreferences=context.getSharedPreferences("shared_prefs",Context.MODE_PRIVATE)

    fun  save(data:String,name:String){
        prefs.edit().putString(name,data).apply()
    }

    fun delete(name: String){
        prefs.edit().remove(name).apply()
    }

    fun value(name: String): String? {
        return prefs.getString(name,null)
    }

    fun ifContain(name:String):Boolean{
        return prefs.contains(name)
    }
}