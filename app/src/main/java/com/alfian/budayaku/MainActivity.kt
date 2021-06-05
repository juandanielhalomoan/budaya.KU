package com.alfian.budayaku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alfian.budayaku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRumahAdat.setOnClickListener {
            val intent = Intent(this, RumahAdatActivity::class.java)
            startActivity(intent)
        }

        binding.btnPakaianAdat.setOnClickListener {
            val intent = Intent(this, PakaianAdatActivity::class.java)
            startActivity(intent)
        }

        binding.btnSenjataAdat.setOnClickListener {
            val intent = Intent(this, SenjataAdatActivity::class.java)
            startActivity(intent)
        }

        binding.btnUpload.setOnClickListener {
            val intent = Intent(this, UploadImageActivity::class.java)
            startActivity(intent)
        }

    }

}