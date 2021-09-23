package com.esaku.barbershop.populaterecyclerview.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esaku.barbershop.databinding.ActivityClosing2Binding

class Closing2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityClosing2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosing2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSelesai.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
    override fun onBackPressed() {
        val intent = Intent(this@Closing2Activity, HomeActivity::class.java)
        startActivity(intent)
        finish()
        super.onBackPressed()
    }

}