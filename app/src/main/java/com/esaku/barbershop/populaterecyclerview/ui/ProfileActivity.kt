package com.esaku.barbershop.populaterecyclerview.ui
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esaku.barbershop.databinding.ActivityProfileBinding
import com.esaku.barbershop.populaterecyclerview.Preferences

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    var preferences = Preferences()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        preferences.setPreferences(this)


        binding.logout.setOnClickListener {
            preferences.preferencesLogout()
            val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
            startActivity(intent)
            finishAffinity()

        }

    }
}