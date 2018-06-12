package com.ruigeng.workyard.lifecyclehelpers

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
import android.support.v7.app.AppCompatActivity

abstract class LifecycleAppCompatActivity : AppCompatActivity() {
    private val registry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry = registry
}