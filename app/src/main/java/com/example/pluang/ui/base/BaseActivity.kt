package com.example.pluang.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VM : BaseViewModel>(clazz: KClass<VM>) : AppCompatActivity() {

    val viewModel: VM by viewModel(clazz)

    @LayoutRes
    protected abstract fun provideLayoutId(): Int


    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected open fun setupObserver() {
        viewModel.messageStringId.observe(this, Observer {

            Toast.makeText(applicationContext, it.data?.let { it1 -> getString(it1) }, Toast.LENGTH_LONG).show()
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupView(savedInstanceState)
        setupObserver()
    }
}