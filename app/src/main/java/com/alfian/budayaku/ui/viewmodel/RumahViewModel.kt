package com.alfian.budayaku.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alfian.budayaku.database.RumahEntity
import com.alfian.budayaku.repository.BudayaRepository

class RumahViewModel(application: Application) : ViewModel() {
    private val mBudayaRepository: BudayaRepository = BudayaRepository(application)

    fun getAllBudaya(): LiveData<PagedList<RumahEntity>> {
        return LivePagedListBuilder(mBudayaRepository.getAllRumah(), 20).build()
    }

    fun searchRumah(sort: String): LiveData<PagedList<RumahEntity>> {
        return LivePagedListBuilder(mBudayaRepository.searchRumah(sort), 20).build()
    }

}