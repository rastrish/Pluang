package com.example.pluang.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pluang.PluangRepository
import com.example.pluang.Util.Resource
import com.example.pluang.Util.Status
import com.example.pluang.data.response.Response
import com.example.pluang.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val pluangRepository: PluangRepository
) : BaseViewModel() {

    val data = MutableLiveData<Resource<Response>>()
    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("Error", throwable.let { it.message })
            data.postValue(Resource(Status.ERROR, null))
        }

    fun getList() {
        data.postValue(Resource(Status.LOADING, null))
        if (checkInternetConnection()) {
            CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
                pluangRepository.getGitList().let {
                    data.postValue(
                        Resource(
                            Status.SUCCESS,
                            it
                        )
                    )
                }
                Log.e("RESPONSE", pluangRepository.getGitList().toString())
            }
        }
    }
}