package com.ruigeng.workyard.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import com.ruigeng.workyard.Service.ApiService
import com.ruigeng.workyard.data.Item
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class MainViewModel @Inject constructor(
        context: Application,
        private val apiService: ApiService
) : AndroidViewModel(context) {

    private var items: MutableLiveData<List<Item>> = MutableLiveData()

    private lateinit var adapter: ItemsAdapter

    fun onStart() {

    }

    fun fetchFeeds(postRecyclerView: RecyclerView) {
        doAsync {

            val compositeDisposable = CompositeDisposable()

            val disposable = apiService.getItems().subscribe {

                uiThread { _ ->
                    items.value = it.items

                    (postRecyclerView.adapter as ItemsAdapter).refresh(items.value)
                }
            }

            compositeDisposable.add(disposable)
            disposable.addTo(compositeDisposable)
            compositeDisposable.dispose()
        }
    }
}