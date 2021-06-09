package com.alfian.budayaku.repository

import android.app.Application
import androidx.paging.DataSource
import com.alfian.budayaku.database.*
import com.alfian.budayaku.helper.SortUtils
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BudayaRepository(application: Application) {
    private val budaya: BudayaDao

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = BudayaRoomDatabase.getDatabase(application)
        budaya = db.budayaDao()
    }

    fun getAllBudaya(sort: String): DataSource.Factory<Int, SenjataEntity> {
        val query = SortUtils.getSortedQuery(sort)
        return budaya.getAllNotes(query)
    }

    fun getAllRumah(): DataSource.Factory<Int, RumahEntity> {
        return budaya.getAllRumah()
    }

    fun getAllPakaian(): DataSource.Factory<Int, PakaianEntity> {
        return budaya.getAllPakaian()
    }

    fun searchSenjata(query: String): DataSource.Factory<Int, SenjataEntity> {
        return budaya.searchSenjata(query)
    }

    fun searchRumah(query: String): DataSource.Factory<Int, RumahEntity> {
        return budaya.searchRumah(query)
    }

    fun searchPakaian(query: String): DataSource.Factory<Int, PakaianEntity> {
        return budaya.searchPakaian(query)
    }

}