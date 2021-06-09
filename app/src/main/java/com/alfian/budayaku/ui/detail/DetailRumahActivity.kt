package com.alfian.budayaku.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alfian.budayaku.databinding.ActivityDetailBudayaBinding
import com.alfian.budayaku.helper.ViewModelFactory
import com.alfian.budayaku.ui.viewmodel.RumahViewModel
import com.bumptech.glide.Glide

class DetailRumahActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBudayaBinding

    private lateinit var rumahViewModel: RumahViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBudayaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.title = ""

        val extras = intent.extras

        if (extras != null) {

            val rumahId = extras.getInt(EXTRA_RUMAH)

            rumahViewModel = obtainViewModel(this)

            rumahViewModel.getAllBudaya().observe(
                this,
                { pageRumah ->

                    for (rumah in pageRumah) {
                        if (rumah.id == rumahId) {
                            binding.content.apply {
                                tvDaerah.text = rumah.namaRumah
                                tvDetailDescription.text = rumah.descriptionRumah
                                supportActionBar?.title = rumah.provinsiRumah

                                Glide.with(this@DetailRumahActivity)
                                    .load(rumah.gambarRumah)
                                    .into(binding.ivDetailImage)

                            }


                        }
                    }
                })


        }

    }


    private fun obtainViewModel(activity: AppCompatActivity): RumahViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(RumahViewModel::class.java)
    }

    companion object {
        const val EXTRA_RUMAH = "extra_rumah"
    }
}