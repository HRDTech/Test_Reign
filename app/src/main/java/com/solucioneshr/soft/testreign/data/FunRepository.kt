package com.solucioneshr.soft.testrappi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solucioneshr.soft.testrappi.network.ApiClient
import com.solucioneshr.soft.testrappi.network.ApiInterface
import com.solucioneshr.soft.testreign.data.DataJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FunRepository {
    private var apiInterface:ApiInterface?=null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun getDataJson(): LiveData<DataJson>{
        val dataResponse = MutableLiveData<DataJson>()

        apiInterface?.getData()?.enqueue(object : Callback<DataJson>{

            override fun onResponse(call: Call<DataJson>, response: Response<DataJson>) {
                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    dataResponse.value = res
                }else{
                    dataResponse.value = null
                }
            }

            override fun onFailure(call: Call<DataJson>, t: Throwable) {
                dataResponse.value = null
            }

        })
        return dataResponse
    }
}