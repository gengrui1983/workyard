package com.ruigeng.workyard.di;

import android.arch.lifecycle.ViewModelProvider;

import com.ruigeng.workyard.viewmodel.WorkyardViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(WorkyardViewModelFactory factory);
}
