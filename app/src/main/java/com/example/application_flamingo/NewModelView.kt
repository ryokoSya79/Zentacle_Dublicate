package com.example.application_flamingo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class NewModelView : ViewModel(){

    val message_start_fun: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }


}

