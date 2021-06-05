package com.alfian.budayaku.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alfian.budayaku.database.SenjataEntity
import com.alfian.budayaku.repository.BudayaRepository

class SenjataViewModel(application: Application) : ViewModel() {
    private val mBudayaRepository: BudayaRepository = BudayaRepository(application)

    fun getAllBudaya(sort: String): LiveData<PagedList<SenjataEntity>> {
        return LivePagedListBuilder(mBudayaRepository.getAllBudaya(sort), 20).build()
    }

    fun searchItems(sort: String): LiveData<PagedList<SenjataEntity>> {
        return LivePagedListBuilder(mBudayaRepository.searchItem(sort), 20).build()
    }

}