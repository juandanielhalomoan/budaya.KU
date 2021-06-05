package com.alfian.budayaku.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class SenjataEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var namaSenjata: String? = null,

    @ColumnInfo(name = "description")
    var descriptionSenjata: String? = null,

    @ColumnInfo(name = "province")
    var provinsiSenjata: String? = null,

    @ColumnInfo(name = "image")
    var gamabarSenjata: Int = 0,

    ) : Parcelable