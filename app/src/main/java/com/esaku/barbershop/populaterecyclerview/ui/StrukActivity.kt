package com.esaku.barbershop.populaterecyclerview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esaku.barbershop.R
import com.esaku.barbershop.databinding.ActivityStrukBinding

class StrukActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStrukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityStrukBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_struk)

    }

}