package com.example.application_flamingo

import android.R.id.edit
import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Search_fragment : Fragment(), ShopStoreAdapter.Listener {
    lateinit var store_itemList: ArrayList<String>
    private val dataModelComent: DataModelComent by activityViewModels()
    val  mAuth = FirebaseAuth.getInstance()
    lateinit var store_image: ArrayList<String>
    lateinit var search_edit_text: EditText

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatere = inflater.inflate(R.layout.fragment_search_fragment, container, false)
        var text_see_all_search: TextView = inflatere.findViewById(R.id.seeAll_search)
        search_edit_text = inflatere.findViewById(R.id.search_edit_text)
        val rc_shop: RecyclerView = inflatere.findViewById(R.id.rc_shop)
        val button_beach: Button = inflatere.findViewById(R.id.button_beach)
        val button_corals: Button = inflatere.findViewById(R.id.button_corals)
        val button_dive: Button = inflatere.findViewById(R.id.button_dive)
        val button_diveParty: Button = inflatere.findViewById(R.id.button_diveParty)
        val button_party: Button = inflatere.findViewById(R.id.button_party)
        val connect_err: ImageView = inflatere.findViewById(R.id.connect_errSearch)
        val anim_connect_err: Animation = AnimationUtils.loadAnimation(context, R.anim.connect_err)
        val NetworkManager = NetworkManager()



        var list_content_1 = ArrayList<String>()
        var list_content_2 = ArrayList<String>()
        var list_content_3 = ArrayList<String>()


        if (NetworkManager.isNetworkAvailable(requireContext())) {
            dataModelComent.map_search_content_1.observe(this, {
                var tag_1 = it["Content_1"]!![1]
                var tag_2 = it["Content_1"]!![2]
                var tag_3 = it["Content_1"]!![3]

                search_edit_text.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        if (!s.toString().contains("\n")) {
                            Log.e("TAG", "Первая строка: Печатается")
                            if(search_edit_text.text.toString() == tag_1 || search_edit_text.text.toString() == tag_2 || search_edit_text.text.toString() == tag_3){
                                dataModelComent.signal_content_1.value = true
                                dataModelComent.textSearch.value = search_edit_text.text.toString()
                            }
                        }
                    }

                    override fun afterTextChanged(s: Editable) {}
                })
            })
            dataModelComent.map_search_content_2.observe(this, {
                var tag_1 = it["Content_2"]!![1]
                var tag_2 = it["Content_2"]!![2]
                var tag_3 = it["Content_2"]!![3]

                search_edit_text.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        if (!s.toString().contains("\n")) {
                            Log.e("TAG", "Первая строка: Печатается")
                            if(search_edit_text.text.toString() == tag_1 || search_edit_text.text.toString() == tag_2 || search_edit_text.text.toString() == tag_3){
                                dataModelComent.signal_content_2.value = true
                                dataModelComent.textSearch.value = search_edit_text.text.toString()
                            }

                        }
                    }

                    override fun afterTextChanged(s: Editable) {}
                })
            })
            dataModelComent.map_search_content_3.observe(this, {
                var tag_1 = it["Content_3"]!![1]
                var tag_2 = it["Content_3"]!![2]
                var tag_3 = it["Content_3"]!![3]

                search_edit_text.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        if (!s.toString().contains("\n")) {
                            Log.e("TAG", "Первая строка: Печатается")
                            if(search_edit_text.text.toString() == tag_1 || search_edit_text.text.toString() == tag_2 || search_edit_text.text.toString() == tag_3){
                                dataModelComent.signal_content_3.value = true
                                dataModelComent.textSearch.value = search_edit_text.text.toString()
                            }

                        }
                    }

                    override fun afterTextChanged(s: Editable) {}
                })

            })


            dataModelComent.map_search_content_title_location_1.observe(this, {
                var location = it["Location_1"]
                var title = it["Title_1"]

                search_edit_text.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        if (!s.toString().contains("\n")) {
                            Log.e("TAG", "Первая строка: Печатается")
                            if(search_edit_text.text.toString() == title || search_edit_text.text.toString() == location){
                                dataModelComent.signal_content_1.value = true
                                dataModelComent.textSearch.value = search_edit_text.text.toString()
                            }

                        }
                    }

                    override fun afterTextChanged(s: Editable) {}
                })
            })

            dataModelComent.map_search_content_title_location_2.observe(this, {
                var location = it["Location_2"]
                var title = it["Title_2"]



                search_edit_text.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        if (!s.toString().contains("\n")) {
                            Log.e("TAG", "Первая строка: Печатается")
                            if(search_edit_text.text.toString() == title || search_edit_text.text.toString() == location){
                                dataModelComent.signal_content_3.value = true
                                dataModelComent.textSearch.value = search_edit_text.text.toString()
                            }

                        }
                    }

                    override fun afterTextChanged(s: Editable) {}
                })
            })

            dataModelComent.map_search_content_title_location_3.observe(this, {
                var location = it["Location_3"]
                var title = it["Title_3"]

                search_edit_text.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        if (!s.toString().contains("\n")) {
                            Log.e("TAG", "Первая строка: Печатается")
                            if(search_edit_text.text.toString() == title || search_edit_text.text.toString() == location){
                                dataModelComent.signal_content_2.value = true
                                dataModelComent.textSearch.value = search_edit_text.text.toString()
                            }

                        }
                    }

                    override fun afterTextChanged(s: Editable) {}
                })
            })


            dataModelComent.item_content_number_1.observe(this,{
                list_content_1.add(it["numberContent"]!!)
                list_content_1.add(it["title"]!!)
                list_content_1.add(it["location"]!!)
                list_content_1.add(it["ocenka"]!!)
                list_content_1.add(it["ocenka_user"]!!)
                list_content_1.add(it["level_diving"]!!)
                list_content_1.add(it["description"]!!)
                list_content_1.add(it["image"]!!)
            })

            dataModelComent.item_content_number_2.observe(this,{
                list_content_2.add(it["numberContent"]!!)
                list_content_2.add(it["title"]!!)
                list_content_2.add(it["location"]!!)
                list_content_2.add(it["ocenka"]!!)
                list_content_2.add(it["ocenka_user"]!!)
                list_content_2.add(it["level_diving"]!!)
                list_content_2.add(it["description"]!!)
                list_content_2.add(it["image"]!!)
            })

            dataModelComent.item_content_number_3.observe(this,{
                list_content_3.add(it["numberContent"]!!)
                list_content_3.add(it["title"]!!)
                list_content_3.add(it["location"]!!)
                list_content_3.add(it["ocenka"]!!)
                list_content_3.add(it["ocenka_user"]!!)
                list_content_3.add(it["level_diving"]!!)
                list_content_3.add(it["description"]!!)
                list_content_3.add(it["image"]!!)
            })

            dataModelComent.message_empty_text.observe(this, {
                if(it){
                    search_edit_text.requestFocus()
                }
            })
        } else {
            connect_err.startAnimation(anim_connect_err)
        }






        val textShaderSeeAll: Shader = LinearGradient(
            250f,
            0f,
            0f,
            text_see_all_search.paint.textSize,
            intArrayOf(Color.parseColor("#0B94EF"), Color.parseColor("#951bfe")),
            floatArrayOf(0.0f, 1f),
            Shader.TileMode.CLAMP
        )
        text_see_all_search.paint.shader = textShaderSeeAll



        rc_shop.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        var adapteris_store = ShopStoreAdapter(this)
        rc_shop.adapter = adapteris_store


        dataModelComent.list_storesLocation.observe(this, {itLocation ->
            dataModelComent.list_storesName.observe(this, {itNameStore ->
                dataModelComent.list_storesRental.observe(this, {itRental ->
                    dataModelComent.list_storesDescription.observe(this, {itDescription ->
                        dataModelComent.list_storesOcenka.observe(this, {itOcenka ->
                            dataModelComent.list_storesOcenkaUser.observe(this, {itOcenkaUser ->

                                store_image = arrayListOf( "ecologicDivers.jpg", "oceanKingDiveShopBali.jpg","baliDiveShop.jpg",  "ScubaDubaDooDiveCentr.jpg")
                                var chetchik_image = 0
                                var chetchik_store = 0

                                for (itemStore in 0..itNameStore.size-1){
                                    chetchik_store++
                                    adapteris_store.addStore(GetDataStore(itLocation[itemStore], itNameStore[itemStore], itRental[itemStore],
                                        store_image[chetchik_image], itDescription[itemStore], itOcenka[itemStore], itOcenkaUser[itemStore], chetchik_store.toString()))
                                    chetchik_image++
                                    if(chetchik_store >= 4){
                                        chetchik_store = 0
                                    }
                                    if(chetchik_image >= 4){chetchik_image=0}


                                }
                            })
                        })
                    })
                })
            })
        })

        button_beach.setOnClickListener {
            dataModelComent.signal_button_beach.value = true
            dataModelComent.signal_content_1.value = true
            dataModelComent.signal_content_2.value = true
            dataModelComent.textSearch.value = "Beach"
        }
        button_corals.setOnClickListener {
            dataModelComent.signal_button_corals.value = true
            dataModelComent.signal_content_1.value = true
            dataModelComent.signal_content_3.value = true
            dataModelComent.textSearch.value = "Corals"
        }
        button_dive.setOnClickListener {
            dataModelComent.signal_button_dive.value = true
            dataModelComent.textSearch.value = "Dive"
        }
        button_diveParty.setOnClickListener {
            dataModelComent.signal_button_diveParty.value = true
            dataModelComent.signal_content_1.value = true
            dataModelComent.signal_content_2.value = true
            dataModelComent.signal_content_3.value = true
            dataModelComent.textSearch.value = "DivePatry"
        }
        button_party.setOnClickListener {
            dataModelComent.signal_button_party.value = true
            dataModelComent.textSearch.value = "Party"
        }

        return inflatere
    }


    companion object {
        @JvmStatic
        fun newInstance() = Search_fragment()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClick(getDataStore: GetDataStore) {
        Toast.makeText(context, "МАГАЗИН БЫЛ НАЖАТ ${getDataStore.name_store}", Toast.LENGTH_LONG).show()

        dataModelComent.item_nameStore.value = getDataStore.name_store
        dataModelComent.item_locationStore.value = getDataStore.name_store
        dataModelComent.item_rentalStore.value = getDataStore.rental_store
        dataModelComent.item_imageStore.value = getDataStore.imageStore
        dataModelComent.item_descriptionStore.value = getDataStore.description
        dataModelComent.item_ocenkaStore.value = getDataStore.ocenka
        dataModelComent.item_ocenkaUserStore.value = getDataStore.ocenka_user
        dataModelComent.item_ocenkaNumberStore.value = getDataStore.numberStore


        dataModelComent.message_openItem_store.value = true
        dataModelComent.signal_main.value = false
    }
}