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

    fun plus(){
        _counter.value = _counter.value?.toInt()?.plus(10)
    }
    fun minus(){
        _counter.value = _counter.value?.toInt()?.minus(10)
    }

}