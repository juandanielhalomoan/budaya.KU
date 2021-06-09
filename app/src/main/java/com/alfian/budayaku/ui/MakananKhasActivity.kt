package com.alfian.budayaku.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfian.budayaku.R
import com.alfian.budayaku.database.ListFoodAdapter
import com.alfian.budayaku.databinding.ActivityMakananKhasBinding
import com.alfian.budayaku.helper.DataMakanan
import com.alfian.budayaku.ui.detail.DetailPakaianActivity
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MakananKhasActivity : AppCompatActivity() {

    private var dataMakanan = arrayListOf<DataMakanan>()

    private lateinit var binding: ActivityMakananKhasBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakananKhasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressLayout.progressView.visibility = View.VISIBLE

        val db = Firebase.firestore

        db.collection("users")
//            .whereEqualTo("pakaian", true)
            .document("makanan")
            .get()
            .addOnSuccessListener { result ->

                val temp = result.data

                val makanan = temp?.getValue("nama_makanan") as ArrayList<*>

                val gambar = temp.getValue("gambar") as ArrayList<*>

                val deskripsi = temp.getValue("deskripsi") as ArrayList<*>

                val daerah = temp.getValue("nama_daerah") as ArrayList<*>

                for ( i in 0 until  makanan.size) {
                    val dataku = DataMakanan()
                    dataku.nama_makanan = makanan[i].toString()
                    dataku.nama_daerah = daerah[i].toString()
                    dataku.deskripsi = deskripsi[i].toString()
                    dataku.gambar = gambar[i].toString()

                    dataMakanan.add(dataku)

                }


                showRecyclerList()
                binding.progressLayout.progressView.visibility = View.GONE

            }
            .addOnFailureListener { exception ->
                Log.w("hasil", "Error getting documents.", exception)
            }



        Log.d("abc", "onCreate: $dataMakanan")

    }

    private fun showRecyclerList() {
        binding.rvBudaya.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListFoodAdapter(dataMakanan)
        binding.rvBudaya.adapter = listUserAdapter
        listUserAdapter.setOnItemClickCallback(object : ListFoodAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataMakanan) {
                val showDetail = Intent(this@MakananKhasActivity, DetailFoodActivity::class.java)
                showDetail.putExtra(DetailFoodActivity.EXTRA_FOOD, data)
                startActivity(showDetail)
            }

        })
    }

}