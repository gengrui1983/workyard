package com.ruigeng.workyard.ui.main

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruigeng.workyard.R
import com.ruigeng.workyard.binding.FragmentDataBindingComponent
import com.ruigeng.workyard.databinding.FragMainBinding
import com.ruigeng.workyard.di.Injectable
import com.ruigeng.workyard.utils.common.AutoClearedValue
import javax.inject.Inject

class MainFragment: Fragment(), Injectable{

    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: AutoClearedValue<FragMainBinding>

    private lateinit var itemsAdapterValue: AutoClearedValue<ItemsAdapter>
    private val dataBindingComponent = FragmentDataBindingComponent(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewDataBinding = DataBindingUtil.inflate<FragMainBinding>(inflater,
                R.layout.frag_main, container, false)

        binding = AutoClearedValue(this, viewDataBinding!!)

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        val itemsAdapter = ItemsAdapter(dataBindingComponent, context as Activity)
        itemsAdapterValue = AutoClearedValue(this, itemsAdapter)

        binding.get().viewmodel = viewModel

        binding.get().recyclerViewItems.apply {
            adapter = itemsAdapterValue.get()

            layoutManager = LinearLayoutManager(context)
        }

        viewModel.run {
            onStart()

            fetchFeeds(binding.get().recyclerViewItems)
        }

    }

    companion object {
        fun getInstance() = MainFragment()
    }
}