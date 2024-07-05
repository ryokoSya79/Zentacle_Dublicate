package com.example.application_flamingo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel(){

    val message: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_to_reg: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_sign_in: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_user_done: MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }
    val message_frag_home: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_see_all: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val item_tema_message: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_location: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_level_diving: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_ocenka_user: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_ocenka: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_image: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val message_full_content: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val item_description: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_numberContent: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val message_back_from_openItem: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_back_from_openItem_search: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_back_from_openItem_seeAll: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_back_from_openItem_diveLogs: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val item_col_coment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val message_show_coments: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_search: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_log_dive: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_profile: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_connect_error: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_logout: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_logout_from_aplication: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_sign_up: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_skip: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}