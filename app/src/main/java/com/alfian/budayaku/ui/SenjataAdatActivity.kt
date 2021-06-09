package com.alfian.budayaku.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfian.budayaku.databinding.ActivitySenjataAdatBinding
import com.alfian.budayaku.database.SenjataEntity
import com.alfian.budayaku.helper.SortUtils
import com.alfian.budayaku.helper.ViewModelFactory
import com.alfian.budayaku.ui.viewmodel.SenjataViewModel
import com.alfian.budayaku.adapter.SenjataPagedListAdapter

class SenjataAdatActivity : AppCompatActivity() {

    private lateinit var adapter: SenjataPagedListAdapter

    private lateinit var senjataViewModel: SenjataViewModel

    private lateinit var binding: ActivitySenjataAdatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySenjataAdatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        senjataViewModel = obtainViewModel(this)
        senjataViewModel.getAllBudaya(SortUtils.NEWEST).observe(this, noteObserver)

        adapter = SenjataPagedListAdapter(this)
        binding.rvListBudaya.layoutManager = LinearLayoutManager(this)
        binding.rvListBudaya.setHasFixedSize(true)
        binding.rvListBudaya.adapter = adapter

        var query = ""
        binding.editTextTextPersonName.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    senjataViewModel.searchSenjata(query).observe(this@SenjataAdatActivity, noteObserver)

                    //hide keyboard
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(v?.windowToken, 0)

                    return true
                }
                return false
            }
        })

        binding.editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                query = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }

    private fun obtainViewModel(activity: AppCompatActivity): SenjataViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(SenjataViewModel::class.java)
    }

    //tes
    private val noteObserver = Observer<PagedList<SenjataEntity>> { noteList ->
        if (noteList != null) {
            adapter.submitList(noteList)
        }
    }


}