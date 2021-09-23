package com.esaku.barbershop.populaterecyclerview.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.esaku.barbershop.databinding.ActivityTransaksi1Binding

class Transaksi1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityTransaksi1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTransaksi1Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.btnpotong.setOnClickListener {
            val intent = Intent(this, TransaksiActivity::class.java)
            startActivity(intent)
        }
        binding.btntransaksi.setOnClickListener {
            val intent = Intent(this,TransaksiActivity::class.java)
            startActivity(intent)
        }
        binding.back1.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}