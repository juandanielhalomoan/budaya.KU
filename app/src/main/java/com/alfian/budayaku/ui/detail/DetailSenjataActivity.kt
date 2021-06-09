package com.alfian.budayaku.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alfian.budayaku.databinding.ActivityDetailBudayaBinding
import com.alfian.budayaku.helper.SortUtils
import com.alfian.budayaku.helper.ViewModelFactory
import com.alfian.budayaku.ui.viewmodel.SenjataViewModel
import com.bumptech.glide.Glide

class DetailSenjataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBudayaBinding

    private lateinit var senjataViewModel: SenjataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBudayaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.title = ""

        val extras = intent.extras

        if (extras != null) {

            val senjataId = extras.getInt(EXTRA_SENJATA)

            senjataViewModel = obtainViewModel(this)

            senjataViewModel.getAllBudaya(SortUtils.NEWEST).observe(
                this,
                { pageSenjata ->

                    for (senjata in pageSenjata) {
                        if (senjata.id == senjataId) {
                            binding.content.apply {
                                tvDaerah.text = senjata.namaSenjata
                                tvDetailDescription.text = senjata.descriptionSenjata
                                supportActionBar?.title = senjata.provinsiSenjata

                                Glide.with(this@DetailSenjataActivity)
                                    .load(senjata.gamabarSenjata)
                                    .into(binding.ivDetailImage)
                            }
                        }
                    }
                })


        }


    }

    private fun obtainViewModel(activity: AppCompatActivity): SenjataViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(SenjataViewModel::class.java)
    }

    companion object {
        const val EXTRA_SENJATA = "extra_senjata"
    }
}