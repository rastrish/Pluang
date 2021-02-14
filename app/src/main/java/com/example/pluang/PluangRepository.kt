package com.example.pluang

import com.example.pluang.data.PluangNetworkService
import com.example.pluang.data.response.Response

class PluangRepository(private val pluangNetworkService: PluangNetworkService){

    suspend fun getGitList() : Response{
        return pluangNetworkService.getGitList("java","weekly")
    }
}