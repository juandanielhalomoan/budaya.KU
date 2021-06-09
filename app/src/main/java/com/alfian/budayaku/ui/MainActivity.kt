package com.alfian.budayaku.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alfian.budayaku.AboutActivity
import com.alfian.budayaku.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        FirebaseApp.initializeApp(this)

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

        binding.btnMakananKhas.setOnClickListener {
            val intent = Intent(this, MakananKhasActivity::class.java)
            startActivity(intent)
        }

        binding.btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }





    }


}