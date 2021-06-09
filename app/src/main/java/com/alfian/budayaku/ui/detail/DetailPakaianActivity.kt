package com.alfian.budayaku.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alfian.budayaku.databinding.ActivityDetailBudayaBinding
import com.alfian.budayaku.helper.ViewModelFactory
import com.alfian.budayaku.ui.viewmodel.PakaianViewModel
import com.bumptech.glide.Glide

class DetailPakaianActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBudayaBinding

    private lateinit var pakaianViewModel: PakaianViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBudayaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.title = ""

        val extras = intent.extras

        if (extras != null) {

            val pakaianId = extras.getInt(EXTRA_PAKAIAN)

            pakaianViewModel = obtainViewModel(this)

            pakaianViewModel.getAllBudaya().observe(
                this,
                { pagePakaian ->

                    for (pakaian in pagePakaian) {
                        if (pakaian.id == pakaianId) {
                            binding.content.apply {
                                tvDaerah.text = pakaian.namaPakaian
                                tvDetailDescription.text = pakaian.descriptionPakaian
                                supportActionBar?.title = pakaian.provinsiPakaian

                                Glide.with(this@DetailPakaianActivity)
                                    .load(pakaian.gambarPakaian)
                                    .into(binding.ivDetailImage)

                            }


                        }
                    }
                })
        }

    }


    private fun obtainViewModel(activity: AppCompatActivity): PakaianViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(PakaianViewModel::class.java)
    }

    companion object {
        const val EXTRA_PAKAIAN = "extra_rumah"
    }

}