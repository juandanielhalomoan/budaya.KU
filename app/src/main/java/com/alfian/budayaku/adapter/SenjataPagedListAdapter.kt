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
import com.alfian.budayaku.database.SenjataEntity
import com.alfian.budayaku.ui.detail.DetailSenjataActivity
import com.bumptech.glide.Glide


class SenjataPagedListAdapter(private val activity: Activity) : PagedListAdapter<SenjataEntity, SenjataPagedListAdapter.BudayaViewHolder>(
    DIFF_CALLBACK
) {

    inner class BudayaViewHolder (private val binding: ItemListBudayaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(senjataEntity: SenjataEntity) {
            with(binding) {
                tvNama.text = senjataEntity.namaSenjata
                tvDaerah.text = senjataEntity.provinsiSenjata
                tvDeskripsi.text = senjataEntity.descriptionSenjata

                Glide.with(itemView.context)
                    .load(senjataEntity.gamabarSenjata)
                    .into(binding.imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailSenjataActivity::class.java)
                    intent.putExtra(DetailSenjataActivity.EXTRA_SENJATA, senjataEntity.id)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<SenjataEntity> = object : DiffUtil.ItemCallback<SenjataEntity>() {
            override fun areItemsTheSame(oldSenjataEntity: SenjataEntity, newSenjataEntity: SenjataEntity): Boolean {
                return oldSenjataEntity.namaSenjata == newSenjataEntity.namaSenjata && oldSenjataEntity.descriptionSenjata == newSenjataEntity.descriptionSenjata
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldSenjataEntity: SenjataEntity, newSenjataEntity: SenjataEntity): Boolean {
                return oldSenjataEntity == newSenjataEntity
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudayaViewHolder {
        val binding = ItemListBudayaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BudayaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudayaViewHolder, position: Int) {
        holder.bind(getItem(position) as SenjataEntity)
    }

}
