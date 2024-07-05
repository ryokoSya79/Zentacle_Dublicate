package com.example.application_flamingo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BlankViewModel : ViewModel() {
    val vuvuvuv: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}