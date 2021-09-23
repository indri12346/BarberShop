package com.esaku.barbershop.populaterecyclerview

import android.content.Context
import android.content.SharedPreferences

class Preferences {
    var APP_NAME = "BARBERSHOP"

    private var log_status = "log_status"
    private var token_type = "token_type"
    private var no_bukti = "no_bukti"
    private var nik = "nik"
    private var nama = "nama"
    private var alamat = "kode_lokasi"
    private var token = "token"
    private var noHp = "no_hp"
    private var kodePP = "kodepp"


    var sp : SharedPreferences? = null
    var spEditor: SharedPreferences.Editor? = null



    fun setPreferences(context: Context) {
        sp = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE)
        spEditor = sp?.edit()

    }
    fun saveLogStatus(value: Boolean) {
        spEditor!!.putBoolean(log_status, value)
        spEditor!!.commit()
    }

    fun getLogStatus(): Boolean {
        return sp!!.getBoolean(log_status, false)
    }

     fun preferencesLogout(){
         spEditor!!.clear()
         spEditor!!.commit()
     }

    fun saveToken(value: String?) {
        spEditor!!.putString(token, value)
        spEditor!!.commit()
    }

    fun saveTokenType(value: String?) {
        spEditor!!.putString(token_type, value)
        spEditor!!.commit()
    }

    fun getTokenType(): String? {
        return sp!!.getString(token_type, "N/A")
    }

    fun getToken(): String? {
        return sp!!.getString(token, "N/A")
    }

}