package com.example.application_flamingo

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModelComent : ViewModel(){

    val constanta: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_numberContent: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val col_coment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val message_back_from_coment: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val item_ocenka: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val list_storesName = MutableLiveData<List<String>>(emptyList())
    val list_storesLocation = MutableLiveData<List<String>>(emptyList())
    val list_storesRental = MutableLiveData<List<String>>(emptyList())
    val list_storesDescription = MutableLiveData<List<String>>(emptyList())
    val list_storesOcenka = MutableLiveData<List<String>>(emptyList())
    val list_storesOcenkaUser = MutableLiveData<List<String>>(emptyList())
    val list_storesNumber = MutableLiveData<List<String>>(emptyList())

    val map_search_content_1 = MutableLiveData<Map<String, List<String>>>(emptyMap())
    val map_search_content_2 = MutableLiveData<Map<String, List<String>>>(emptyMap())
    val map_search_content_3 = MutableLiveData<Map<String, List<String>>>(emptyMap())

    val map_search_content_title_location_1 = MutableLiveData<Map<String, String>>(emptyMap())
    val map_search_content_title_location_2 = MutableLiveData<Map<String, String>>(emptyMap())
    val map_search_content_title_location_3 = MutableLiveData<Map<String, String>>(emptyMap())

    val item_content_number_1 = MutableLiveData<Map<String, String>>(emptyMap())
    val item_content_number_2 = MutableLiveData<Map<String, String>>(emptyMap())
    val item_content_number_3 = MutableLiveData<Map<String, String>>(emptyMap())

    val message_empty_text: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val signal_content_1: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val signal_content_2: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val signal_content_3: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }


    val signal_contentUser_1: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val signal_contentUser_2: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val signal_contentUser_3: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val textSearch: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val signal_search: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val signal_main: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val signal_button_beach: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val signal_button_corals: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val signal_button_dive: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val signal_button_diveParty: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val signal_button_party: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val signal_seeAll: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val item_nameStore: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_locationStore: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_rentalStore: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_imageStore: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_descriptionStore: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_ocenkaStore: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_ocenkaUserStore: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val item_ocenkaNumberStore: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val message_openItem_store: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_startContent: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val item_button_block_logDive_1: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val item_button_block_logDive_2: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val item_button_block_logDive_3: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val item_button_block_logDive_store_1: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val item_button_block_logDive_store_2: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val item_button_block_logDive_store_3: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val item_button_block_logDive_store_4: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    var list_item_contentDiveLogs_1 = MutableLiveData<List<String>>(emptyList())
    var list_item_contentDiveLogs_2 = MutableLiveData<List<String>>(emptyList())
    val list_item_contentDiveLogs_3 = MutableLiveData<List<String>>(emptyList())

    val list_item_contentDiveLogsStore_1 = MutableLiveData<List<String>>(emptyList())
    val list_item_contentDiveLogsStore_2 = MutableLiveData<List<String>>(emptyList())
    val list_item_contentDiveLogsStore_3 = MutableLiveData<List<String>>(emptyList())
    val list_item_contentDiveLogsStore_4 = MutableLiveData<List<String>>(emptyList())

    val message_back_diveLogs: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_dubl_back_diveLogs: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_prob_back: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val name_user: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val number_diveLogs: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val number_visite: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val number_visite_2: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val proverka_start: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val sign_in_user: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val name_sqlite: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val about_me: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val message_openGalerery: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val item_image: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val clue_proverka: MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }


}

