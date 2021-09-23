package com.esaku.barbershop.populaterecyclerview.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esaku.barbershop.R

class SplashhActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashh)
//           iv_spalashh .alpha = 0f
//            iv_spalashh.animate().setDuration(1500).alpha(1f).withEndAction{
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                finish()
            }
    }
//}