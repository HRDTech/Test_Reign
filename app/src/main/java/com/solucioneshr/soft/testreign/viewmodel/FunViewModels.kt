package com.solucioneshr.soft.testrappi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.solucioneshr.soft.testrappi.data.FunRepository
import com.solucioneshr.soft.testreign.data.DataJson

class FunViewModels(application: Application): AndroidViewModel(application) {
    private var funRepository: FunRepository? = null
    var getModelLiveData: LiveData<DataJson>? = null

    init {
        funRepository = FunRepository()
        getModelLiveData = MutableLiveData()
    }

    fun getDataJson(){
        getModelLiveData = funRepository?.getDataJson()
    }

}