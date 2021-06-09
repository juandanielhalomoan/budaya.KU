package com.alfian.budayaku.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfian.budayaku.R
import com.alfian.budayaku.databinding.ItemListBudayaBinding
import com.alfian.budayaku.helper.DataMakanan
import com.bumptech.glide.Glide

class ListFoodAdapter(private val listUser: ArrayList<DataMakanan>) :
    RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {

    private lateinit var binding: ItemListBudayaBinding
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_budaya, parent, false)
        binding = ItemListBudayaBinding.bind(view)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
        Glide.with(holder.itemView.context)
            .load(user.gambar)
            .into(binding.imgPoster)

        binding.tvNama.text = user.nama_makanan
        binding.tvDaerah.text = user.nama_daerah
        binding.tvDeskripsi.text = user.deskripsi

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataMakanan)
    }

}