package com.esaku.barbershop.populaterecyclerview.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esaku.barbershop.databinding.ActivityClosing1Binding

class Closing1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityClosing1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosing1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnselesai.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@Closing1Activity, HomeActivity::class.java)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }
}