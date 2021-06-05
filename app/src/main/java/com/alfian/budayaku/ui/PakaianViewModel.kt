package com.alfian.budayaku.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alfian.budayaku.database.PakaianEntity
import com.alfian.budayaku.database.RumahEntity
import com.alfian.budayaku.repository.BudayaRepository

class PakaianViewModel(application: Application) : ViewModel() {
    private val mBudayaRepository: BudayaRepository = BudayaRepository(application)

    fun getAllBudaya(): LiveData<PagedList<PakaianEntity>> {
        return LivePagedListBuilder(mBudayaRepository.getAllPakaian(), 20).build()
    }

//    fun searchItems(sort: String): LiveData<PagedList<Budaya>> {
//        return LivePagedListBuilder(mBudayaRepository.searchItem(sort), 20).build()
//    }

}