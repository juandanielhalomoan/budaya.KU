package com.alfian.budayaku.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alfian.budayaku.databinding.ItemListBudayaBinding
import com.alfian.budayaku.database.RumahEntity
import com.alfian.budayaku.ui.detail.DetailRumahActivity
import com.bumptech.glide.Glide


class RumahPagedListAdapter(private val activity: Activity) : PagedListAdapter<RumahEntity, RumahPagedListAdapter.BudayaViewHolder>(
    DIFF_CALLBACK
) {

    inner class BudayaViewHolder (private val binding: ItemListBudayaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rumahEntity: RumahEntity) {
            with(binding) {
                tvDaerah.text = rumahEntity.namaRumah
                tvNama.text = rumahEntity.provinsiRumah
                tvDeskripsi.text = rumahEntity.descriptionRumah

                Glide.with(itemView.context)
                    .load(rumahEntity.gambarRumah)
                    .into(binding.imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailRumahActivity::class.java)
                    intent.putExtra(DetailRumahActivity.EXTRA_RUMAH, rumahEntity.id)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<RumahEntity> = object : DiffUtil.ItemCallback<RumahEntity>() {
            override fun areItemsTheSame(oldEntity: RumahEntity, newEntity: RumahEntity): Boolean {
                return oldEntity.namaRumah == newEntity.namaRumah && oldEntity.descriptionRumah == newEntity.descriptionRumah
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldEntity: RumahEntity, newEntity: RumahEntity): Boolean {
                return oldEntity == newEntity
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudayaViewHolder {
        val binding = ItemListBudayaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BudayaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudayaViewHolder, position: Int) {
        holder.bind(getItem(position) as RumahEntity)
    }

}
