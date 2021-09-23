package com.esaku.barbershop.populaterecyclerview.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.esaku.barbershop.databinding.ActivityHomeBinding
import com.esaku.barbershop.populaterecyclerview.adapters.TransaksiAdapter
import com.esaku.barbershop.populaterecyclerview.apihelper.ApiClient
import com.esaku.barbershop.populaterecyclerview.intToCurrency
import com.esaku.barbershop.populaterecyclerview.models.ArrkunjItem
import com.esaku.barbershop.populaterecyclerview.models.ModelInformasi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    val dataAdapter= TransaksiAdapter()
    val data=ArrayList<ModelInformasi>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = dataAdapter
        }
//

        binding.btncetakstruk.setOnClickListener {
            val intent = Intent(this, TransaksiActivity::class.java)
            startActivity(intent)

        }
        binding.imgbarber.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

        }
        binding.closing.setOnClickListener {
            val intent = Intent(this, ClosingActivity::class.java)
            startActivity(intent)
        }
        val currentDate: String =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        gettrans(tanggal = currentDate, kodeBarber = "TESBRB01")

    }

    fun gettrans(tanggal: String?, kodeBarber: String?) {
        val utilsapi= ApiClient().getApiService(this)
        utilsapi?.transaksi(tanggal, kodeBarber)?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val obj = JSONObject(response.body()!!.string())
                        val gson = Gson()
                        val myobj: JSONObject = obj.getJSONObject("success")
                        val type: Type = object :
                            TypeToken<java.util.ArrayList<ArrkunjItem>?>() {}.type
                        val dataMapel: java.util.ArrayList<ArrkunjItem> =
                            gson.fromJson(myobj.optString("arrkunj"), type)
//                        val data=response.body()!!.arrkunj
//                        if (data != null) {
                        dataAdapter.initData(dataMapel)
                        var nilai = 0
                        for (i in 0 until dataMapel.size)
                        {
                            if (dataMapel[i].total!=null){
                                nilai+= dataMapel[i].total!!
                            }
                        }

                        binding.pendapatan.text= intToCurrency(nilai)

//                        dataMapel[0].arrkunj?.let { dataAdapter.initData(it) }
//                        }else{
//                            Toast.makeText(this@HomeActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    prefe7rences.saveToken(modelLogin.body()!!.token.toString())
//                    preferences.saveTokenType(modelLogin.body()!!.tokenType.toString())
//                    preferences.saveLogStatus(true)
//                    val intent = Intent(this@HomeActivity, HomeActivity::class.java)
//                    startActivity(intent)
//                    finish()
                    } else {
                        Toast.makeText(this@HomeActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else if (response.code() == 422) {
                    Toast.makeText(
                        this@HomeActivity,
                        "Username/Password masih kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (response.code() == 401) {
                    Toast.makeText(
                        this@HomeActivity,
                        "Username/Password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (response.code() == 403) {
                    Toast.makeText(this@HomeActivity, "Token Invalid", Toast.LENGTH_SHORT).show()
                } else if (response.code() == 404 || response.code() == 405) {
                    Toast.makeText(
                        this@HomeActivity,
                        "Terjadi kesalahan server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Yakin ingin keluar?")
        builder.setCancelable(true)
        builder.setPositiveButton("Ya") { _, _ -> super.onBackPressed() }
        builder.setNegativeButton("Tidak") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()
    }
//    override fun onBackPressed() {
//        val builder = AlertDialog.Builder(this)
//        builder.setMessage("Yakin ingin keluar?")
//        builder.setCancelable(true)
//        builder.setPositiveButton("Ya") { _, _ -> super.onBackPressed() }
//        builder.setNegativeButton("Tidak") { _, _ ->
//
//        }
//        val dialog = builder.create()
//        dialog.show()
//    }


}