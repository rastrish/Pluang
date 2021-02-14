package com.example.pluang.data

import com.example.pluang.data.response.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface PluangNetworkService{

  @GET(EndPoints.REPO)
  suspend fun getGitList(@Query("lang")lang : String , @Query("since") since : String) : Response

}