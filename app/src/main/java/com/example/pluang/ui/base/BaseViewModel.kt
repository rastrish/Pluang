package com.example.pluang.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pluang.R
import com.example.pluang.Util.NetworkHelper
import com.example.pluang.Util.Resource
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.java.KoinJavaComponent.inject

abstract class BaseViewModel : ViewModel() , KoinComponent{

    private val networkHelper : NetworkHelper by inject()
    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()

    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    protected fun checkInternetConnection(): Boolean =
        if (networkHelper.isNetworkConnected()) true
        else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }

}