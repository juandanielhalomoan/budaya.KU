package com.alfian.budayaku.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alfian.budayaku.databinding.ItemListBudayaBinding
import com.alfian.budayaku.database.RumahEntity
import com.bumptech.glide.Glide


class RumahPagedListAdapter(private val activity: Activity) : PagedListAdapter<RumahEntity, RumahPagedListAdapter.BudayaViewHolder>(
    DIFF_CALLBACK
) {

    inner class BudayaViewHolder (private val binding: ItemListBudayaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: RumahEntity) {
            with(binding) {
                tvTitle.text = entity.namaRumah
                tvRelease.text = entity.provinsiRumah
                tvRating.text = entity.descriptionRumah

                Glide.with(itemView.context)
                    .load(entity.gambarRumah)
                    .into(binding.imgPoster)

                itemContainer.setOnClickListener {
//                    val intent = Intent(activity, NoteAddUpdateActivity::class.java)
//                    intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, adapterPosition)
//                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
//                    activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
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
