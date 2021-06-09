package com.alfian.budayaku.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alfian.budayaku.R
import com.alfian.budayaku.databinding.ActivityDetailBudayaBinding
import com.alfian.budayaku.helper.DataMakanan
import com.alfian.budayaku.helper.ViewModelFactory
import com.alfian.budayaku.ui.viewmodel.PakaianViewModel
import com.bumptech.glide.Glide
import com.google.firebase.firestore.auth.User

class DetailFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBudayaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBudayaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.title = ""



        val dataMakanan = intent.getParcelableExtra<DataMakanan>(EXTRA_FOOD) as DataMakanan

        binding.content.tvDaerah.text = dataMakanan.nama_daerah
        binding.content.tvDetailDescription.text = dataMakanan.deskripsi
        supportActionBar?.title = dataMakanan.nama_makanan

        Glide.with(this)
            .load(dataMakanan.gambar)
            .into(binding.ivDetailImage)


    }

    companion object {
        const val EXTRA_FOOD = "extra_rumah"
    }
}