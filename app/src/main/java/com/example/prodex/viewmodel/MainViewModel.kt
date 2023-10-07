package com.example.prodex.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.prodex.models.categories.CategoriesModel
import com.example.prodex.network.ApiClient.apiClient
import com.example.prodex.network.ApiService
import com.example.prodex.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel : ViewModel {
    private var apiService: ApiService? = null

    constructor()

    constructor(apiService: ApiService) {
        this.apiService = apiService
    }


    fun init(context: Context) {
        apiService = context.apiClient().create(ApiService::class.java)
    }


    private val _counter = MutableLiveData<Int>().apply {
        value = 0
    }
    val counter: LiveData<Int> = _counter

//    private val _total = MutableLiveData<Int>().apply {
//        value = _amount.value?.toInt()?.plus(_counter.value?.toInt()!!)
//    }
//    val total: LiveData<Int> = _total

    private val _amount = MutableLiveData<Int>().apply {
        value = 29
    }

    fun plus() {
        _counter.value = _counter.value?.toInt()?.plus(10)
    }

    fun minus() {
        _counter.value = _counter.value?.toInt()?.minus(10)
    }


    fun getCategories() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService?.getCategories()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occurred"))
        }
    }

}