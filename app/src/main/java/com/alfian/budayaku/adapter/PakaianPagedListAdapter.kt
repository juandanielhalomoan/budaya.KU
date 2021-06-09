package com.alfian.budayaku.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alfian.budayaku.database.PakaianEntity
import com.alfian.budayaku.databinding.ItemListBudayaBinding
import com.alfian.budayaku.ui.detail.DetailPakaianActivity
import com.bumptech.glide.Glide


class PakaianPagedListAdapter(private val activity: Activity) : PagedListAdapter<PakaianEntity, PakaianPagedListAdapter.BudayaViewHolder>(
    DIFF_CALLBACK
) {

    inner class BudayaViewHolder (private val binding: ItemListBudayaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pakaian: PakaianEntity) {
            with(binding) {
                tvNama.text = pakaian.provinsiPakaian
                tvDaerah.text = pakaian.namaPakaian
                tvDeskripsi.text = pakaian.descriptionPakaian

                Glide.with(itemView.context)
                    .load(pakaian.gambarPakaian)
                    .into(binding.imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailPakaianActivity::class.java)
                    intent.putExtra(DetailPakaianActivity.EXTRA_PAKAIAN, pakaian.id)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<PakaianEntity> = object : DiffUtil.ItemCallback<PakaianEntity>() {
            override fun areItemsTheSame(oldEntity: PakaianEntity, newEntity: PakaianEntity): Boolean {
                return oldEntity.namaPakaian == newEntity.namaPakaian && oldEntity.descriptionPakaian == newEntity.descriptionPakaian
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldEntity: PakaianEntity, newEntity: PakaianEntity): Boolean {
                return oldEntity == newEntity
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudayaViewHolder {
        val binding = ItemListBudayaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BudayaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudayaViewHolder, position: Int) {
        holder.bind(getItem(position) as PakaianEntity)
    }

}
