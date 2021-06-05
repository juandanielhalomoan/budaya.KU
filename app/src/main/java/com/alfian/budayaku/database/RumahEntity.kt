package com.alfian.budayaku.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class RumahEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var namaRumah: String? = null,

    @ColumnInfo(name = "description")
    var descriptionRumah: String? = null,

    @ColumnInfo(name = "province")
    var provinsiRumah: String? = null,

    @ColumnInfo(name = "image")
    var gambarRumah: Int = 0

) : Parcelable