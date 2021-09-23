package com.esaku.barbershop.populaterecyclerview.ui

import android.app.AlertDialog.Builder
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.esaku.barbershop.databinding.ActivityLoginBinding
import com.esaku.barbershop.populaterecyclerview.Preferences
import com.esaku.barbershop.populaterecyclerview.apihelper.ApiClient
import com.esaku.barbershop.populaterecyclerview.models.ModelLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    var username = ""
    var password = ""
    var preferences = Preferences()

    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        preferences.setPreferences(this)
        checkLogin()

        binding.btnlogin.setOnClickListener {
            username= binding.name.text.toString()
            password= binding.pwd.text.toString()
            login(username,password)
//            if (username!=""&&password!=""){
//            }   else{
//                Toast.makeText(this,"Username atau Password belum terisi!",Toast.LENGTH_LONG).show()
//            }
        }


    }

    private fun checkLogin(){
        if(preferences.getLogStatus()){
            val intent =
                Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        val builder = Builder(this)
        builder.setMessage("Yakin ingin keluar?")
        builder.setCancelable(true)
        builder.setPositiveButton("Ya") { _, _ -> super.onBackPressed() }
        builder.setNegativeButton("Tidak") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()
    }
    fun login(nik: String?, password: String?) {
        val utilsapi= ApiClient().getApiService(this)
        utilsapi?.login(nik, password)?.enqueue(object : Callback<ModelLogin?> {
            override fun onResponse(
                call: Call<ModelLogin?>,
                modelLogin: Response<ModelLogin?>
            ) {
                if (modelLogin.isSuccessful) {
                    if (modelLogin.body() != null) {
                        preferences.saveToken(modelLogin.body()!!.token.toString())
                        preferences.saveTokenType(modelLogin.body()!!.tokenType.toString())
                        preferences.saveLogStatus(true)
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else if (modelLogin.code() == 422) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Username/Password masih kosong",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (modelLogin.code() == 401) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Username/Password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (modelLogin.code() == 403) {
                    Toast.makeText(this@LoginActivity, "Token Invalid", Toast.LENGTH_SHORT).show()
                } else if (modelLogin.code() == 404 || modelLogin.code() == 405) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Terjadi kesalahan server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ModelLogin?>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show()
            }
        })
    }


}