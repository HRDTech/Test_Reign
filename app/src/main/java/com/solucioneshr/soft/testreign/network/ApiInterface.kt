package com.solucioneshr.soft.testrappi.network

import retrofit2.Call
import com.solucioneshr.soft.testreign.data.DataJson
import retrofit2.http.GET

interface ApiInterface {

    @GET("search_by_date?query=mobile")
    fun getData(): Call<DataJson>

}