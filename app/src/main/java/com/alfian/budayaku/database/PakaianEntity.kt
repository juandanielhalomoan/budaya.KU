package com.alfian.budayaku.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class PakaianEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var namaPakaian: String? = null,

    @ColumnInfo(name = "description")
    var descriptionPakaian: String? = null,

    @ColumnInfo(name = "province")
    var provinsiPakaian: String? = null,

    @ColumnInfo(name = "image")
    var gambarPakaian: Int = 0

) : Parcelable