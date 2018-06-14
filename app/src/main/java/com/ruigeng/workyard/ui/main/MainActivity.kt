package com.ruigeng.workyard.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ruigeng.workyard.R
import com.ruigeng.workyard.lifecyclehelpers.LifecycleAppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_base.view.*
import javax.inject.Inject

class MainActivity: LifecycleAppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var fragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        fragment = MainFragment.getInstance()

        supportFragmentManager.beginTransaction().replace(R.id.layoutMain, fragment).commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}