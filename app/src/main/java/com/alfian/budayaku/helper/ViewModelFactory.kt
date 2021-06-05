package com.alfian.budayaku.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alfian.budayaku.ui.SenjataViewModel
import com.alfian.budayaku.ui.PakaianViewModel
import com.alfian.budayaku.ui.RumahViewModel

class ViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SenjataViewModel::class.java)) {
            return SenjataViewModel(mApplication) as T
        }
        if (modelClass.isAssignableFrom(RumahViewModel::class.java)) {
            return RumahViewModel(mApplication) as T
        }
        if (modelClass.isAssignableFrom(PakaianViewModel::class.java)) {
            return PakaianViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}