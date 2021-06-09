package com.alfian.budayaku.helper

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataMakanan(
    var deskripsi: String = "",
    var gambar: String = "",
    var nama_daerah: String = "",
    var nama_makanan: String = ""
) : Parcelable
