package com.example.pluang.di

import com.example.pluang.ui.detailedview.DetailedViewModel
import com.example.pluang.PluangRepository
import com.example.pluang.data.Networking
import com.example.pluang.data.PluangNetworkService
import com.example.pluang.Util.NetworkHelper
import com.example.pluang.ui.main.MainAdapter
import com.example.pluang.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val Koinmodule
    get() = module {

        fun providePluangNetworkService(): PluangNetworkService =
            Networking.createPluangNetworkService()

        single { providePluangNetworkService() }
        single { PluangRepository(get()) }
        single { NetworkHelper(androidContext()) }
        single { MainAdapter() }
        viewModel { MainViewModel(get()) }
        viewModel { DetailedViewModel() }

    }