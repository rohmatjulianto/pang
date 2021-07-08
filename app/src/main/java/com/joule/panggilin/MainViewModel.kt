package com.joule.panggilin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _menu = MutableLiveData<ArrayList<String>>().apply {
        val arrMenu : ArrayList<String> = ArrayList()
        for(i in 1..8){
            arrMenu.add("Menu $i")
        }
        value = arrMenu
    }

    private val _menuPreview = MutableLiveData<ArrayList<String>>().apply {
        val arrMenu : ArrayList<String> = ArrayList()
        for(i in 1..4){
            arrMenu.add("Menu $i")
        }
        value = arrMenu
    }

    val menuPreview: LiveData<ArrayList<String>> = _menuPreview
    val menu : LiveData<ArrayList<String>> = _menu
}