package com.example.prodex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel {
    constructor()

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

}