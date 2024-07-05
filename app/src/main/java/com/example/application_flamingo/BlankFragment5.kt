package com.example.application_flamingo

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.application_flamingo.db.DbManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class BlankFragment5 : Fragment(), ContentAdapter.Listener, ShopStoreAdapter.Listener{
    var perevod_open_content = false
    lateinit var content_itemList: ArrayList<String>
    lateinit var content_image: ArrayList<String>
    lateinit var shop_itemList: ArrayList<String>
    lateinit var shop_image: ArrayList<String>
    lateinit var rc_view: RecyclerView
    lateinit var databaseFireing: DatabaseReference
    private val dataModel: DataModel by activityViewModels()
    private val dataModelComent: DataModelComent by activityViewModels()
    var true_false = false
    val databaseStore: DatabaseReference = FirebaseDatabase.getInstance().getReference("shop_diving")
    lateinit var store_itemListName: ArrayList<String>
    lateinit var store_itemListLocation: ArrayList<String>
    lateinit var store_itemListRental: ArrayList<String>
    lateinit var store_itemListDescription: ArrayList<String>
    lateinit var store_itemListOcenka: ArrayList<String>
    lateinit var store_itemListOcenkaUser: ArrayList<String>
    lateinit var store_itemListNumberStore: ArrayList<String>
    lateinit var databaseUserVisite: DatabaseReference



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }


    }
    private var index = 0


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatere = inflater.inflate(R.layout.fragment_blank5, container, false)
        val name_text : TextView = inflatere.findViewById(R.id.name_main_act)
        val rc_shop: RecyclerView = inflatere.findViewById(R.id.rc_shop)
        val NetworkManager = NetworkManager()
        val connect_err: ImageView = inflatere.findViewById(R.id.connect_errMain)
        val anim_connect_err: Animation = AnimationUtils.loadAnimation(context, R.anim.connect_err)

        val dbManager = DbManager(requireContext())
        dbManager.openDb()
        var dataList = dbManager.readDbData()

        name_text.text = dataList[4].toString()
        dataModelComent.name_sqlite.value = dataList[4].toString()
        dbManager.closeDb()



        if (NetworkManager.isNetworkAvailable(requireContext())) {
            val databaseUserDiveLogs = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("dive_logs")
            val valueUserDiveLogs = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val diveLogsUser = snapshot.value.toString()
                    dataModelComent.number_diveLogs.value = diveLogsUser

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }
            databaseUserDiveLogs.addListenerForSingleValueEvent(valueUserDiveLogs)

            databaseUserVisite = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("visite_site")
            val valueUserVisite = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val visiteUser = snapshot.value.toString()
                    dataModelComent.number_visite.value = visiteUser

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }
            databaseUserVisite.addListenerForSingleValueEvent(valueUserVisite)

            val databaseUserAboutMe = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("about_me")
            val valueUserAboutMe = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val aboutMeUser = snapshot.value.toString()
                    dataModelComent.about_me.value = aboutMeUser

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }
            databaseUserAboutMe.addListenerForSingleValueEvent(valueUserAboutMe)
        } else {
            connect_err.startAnimation(anim_connect_err)
        }

        dbManager.closeDb()

        store_itemListName = arrayListOf<String>()
        store_itemListLocation = arrayListOf<String>()
        store_itemListRental = arrayListOf<String>()
        store_itemListDescription = arrayListOf<String>()
        store_itemListOcenka = arrayListOf<String>()
        store_itemListOcenkaUser = arrayListOf<String>()
        store_itemListNumberStore = arrayListOf<String>()

        dataModelComent.constanta.value = true



        val textSeeAll: TextView = inflatere.findViewById(R.id.seeAll)
        val textShaderSeeAll: Shader = LinearGradient(
            250f,
            0f,
            0f,
            textSeeAll.paint.textSize,
            intArrayOf(Color.parseColor("#0B94EF"), Color.parseColor("#951bfe")),
            floatArrayOf(0.0f, 1f),
            TileMode.CLAMP
        )
        textSeeAll.paint.shader = textShaderSeeAll
        textSeeAll.setOnClickListener{
            dataModel.message_see_all.value = true
        }

        rc_view= inflatere.findViewById(R.id.rc_view)
        rc_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        rc_shop.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        var adapteris_store = ShopStoreAdapter(this)
        rc_shop.adapter = adapteris_store

        fun getFireData(listener: ContentAdapter.Listener){
            databaseFireing = FirebaseDatabase.getInstance().getReference("contents")
            val valueList = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        var adapteris = ContentAdapter(listener)
                        rc_view.adapter = adapteris
                        var chetchik_image = 0


                        for(content_snapshot in snapshot.children){

                            content_itemList = arrayListOf<String>()
                            content_image = arrayListOf<String>("three_content.jpg", "two_content.jpg", "one_content.jpg")

                            val contents = content_snapshot.getValue(GetDataContent :: class.java)
                            content_itemList.add(contents!!.level_diving!!)
                            content_itemList.add(contents!!.ocenka_user!!)
                            content_itemList.add(contents!!.location!!)
                            content_itemList.add(contents!!.title!!)
                            content_itemList.add(contents!!.ocenka!!)
                            content_itemList.add(contents!!.description!!)
                            content_itemList.add(contents!!.numberContent!!)


                            adapteris.addContent(GetDataContent(content_itemList[0], content_itemList[1],content_itemList[2],content_itemList[3],
                                                                content_itemList[4], content_image[chetchik_image], content_itemList[5], content_itemList[6]))

                            if(chetchik_image+1 == 1){
                                dataModelComent.map_search_content_title_location_1.value = mapOf("Location_${chetchik_image+1}" to content_itemList[2],
                                    "Title_${chetchik_image+1}" to content_itemList[3])
                                Log.e("CHECK", "Location_${chetchik_image+1} to ${content_itemList[2]}")
                            }
                            if(chetchik_image+1 == 2){
                                dataModelComent.map_search_content_title_location_2.value = mapOf("Location_${chetchik_image+1}" to content_itemList[2],
                                    "Title_${chetchik_image+1}" to content_itemList[3])
                                Log.e("CHECK", "Location_${chetchik_image+1} to ${content_itemList[2]}")
                            }
                            if(chetchik_image+1 == 3){
                                dataModelComent.map_search_content_title_location_3.value = mapOf("Location_${chetchik_image+1}" to content_itemList[2],
                                    "Title_${chetchik_image+1}" to content_itemList[3])
                                Log.e("CHECK", "Location_${chetchik_image+1} to ${content_itemList[2]}")
                            }


                            chetchik_image++
                            if(chetchik_image >= 3){chetchik_image=0}


                            if(contents!!.numberContent!! == "1"){
                                dataModelComent.item_content_number_1.value = mapOf("level_diving" to content_itemList[0], "ocenka_user" to content_itemList[1],
                                                                                    "location" to content_itemList[2], "title" to content_itemList[3],
                                                                                    "ocenka" to content_itemList[4], "image" to "one_content.jpg",
                                                                                    "description" to content_itemList[5], "numberContent" to content_itemList[6])

                                dataModelComent.list_item_contentDiveLogs_1.value = listOf(content_itemList[0], content_itemList[1], content_itemList[2], content_itemList[3],
                                content_itemList[4],"three_content.jpg", content_itemList[5], content_itemList[6])
                            }
                            if(contents!!.numberContent!! == "2"){
                                dataModelComent.item_content_number_2.value = mapOf("level_diving" to content_itemList[0], "ocenka_user" to content_itemList[1],
                                    "location" to content_itemList[2], "title" to content_itemList[3],
                                    "ocenka" to content_itemList[4], "image" to "two_content.jpg",
                                    "description" to content_itemList[5], "numberContent" to content_itemList[6])

                                dataModelComent.list_item_contentDiveLogs_2.value = listOf(content_itemList[0], content_itemList[1], content_itemList[2], content_itemList[3],
                                    content_itemList[4],"one_content.jpg", content_itemList[5], content_itemList[6])
                            }
                            if(contents!!.numberContent!! == "3"){
                                dataModelComent.item_content_number_3.value = mapOf("level_diving" to content_itemList[0], "ocenka_user" to content_itemList[1],
                                    "location" to content_itemList[2], "title" to content_itemList[3],
                                    "ocenka" to content_itemList[4], "image" to "three_content.jpg",
                                    "description" to content_itemList[5], "numberContent" to content_itemList[6])

                                dataModelComent.list_item_contentDiveLogs_3.value = listOf(content_itemList[0], content_itemList[1], content_itemList[2], content_itemList[3],
                                    content_itemList[4],"two_content.jpg", content_itemList[5], content_itemList[6])
                            }




                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }
            }
            databaseFireing.addListenerForSingleValueEvent(valueList)


            val databaseStore: DatabaseReference = FirebaseDatabase.getInstance().getReference("shop_diving")
            val valueStore = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var numValStore = 0

                        var databaseShop: DatabaseReference = FirebaseDatabase.getInstance().getReference("shop_diving")
                        val valueShop = object : ValueEventListener {
                            @SuppressLint("FragmentLiveDataObserve")
                            override fun onDataChange(snapshot: DataSnapshot) {
                                var chetchik_image_shop = 0
                                for(valShop in snapshot.children){

                                    val shops = valShop.getValue(GetDataStore :: class.java)

                                    shop_itemList = arrayListOf<String>()
                                    shop_image = arrayListOf( "ecologicDivers.jpg", "oceanKingDiveShopBali.jpg","baliDiveShop.jpg",  "ScubaDubaDooDiveCentr.jpg")


                                    store_itemListLocation.add(shops!!.location_store!!)
                                    store_itemListName.add(shops!!.name_store!!)
                                    store_itemListRental.add(shops!!.rental_store!!)
                                    store_itemListDescription.add(shops!!.description!!)
                                    store_itemListOcenka.add(shops!!.ocenka!!)
                                    store_itemListOcenkaUser.add(shops!!.ocenka_user!!)
                                    store_itemListNumberStore.add(shops!!.numberStore!!)


                                    shop_itemList.add(shops!!.location_store!!)
                                    shop_itemList.add(shops!!.name_store!!)
                                    shop_itemList.add(shops!!.rental_store!!)
                                    shop_itemList.add(shops!!.description!!)
                                    shop_itemList.add(shops!!.ocenka!!)
                                    shop_itemList.add(shops!!.ocenka_user!!)
                                    shop_itemList.add(shops!!.numberStore!!)


                                    adapteris_store.addStore(GetDataStore(shop_itemList[0], shop_itemList[1], shop_itemList[2], shop_image[chetchik_image_shop],
                                        shop_itemList[3], shop_itemList[4], shop_itemList[5], shop_itemList[6]))

                                    dataModelComent.list_item_contentDiveLogsStore_1.value = listOf(shop_itemList[0],shop_itemList[1],shop_itemList[2],"ecologicDivers.jpg",
                                        shop_itemList[3],shop_itemList[4],shop_itemList[5],shop_itemList[6])
                                    dataModelComent.list_item_contentDiveLogsStore_2.value = listOf(shop_itemList[0],shop_itemList[1],shop_itemList[2],"oceanKingDiveShopBali.jpg",
                                        shop_itemList[3],shop_itemList[4],shop_itemList[5],shop_itemList[6])
                                    dataModelComent.list_item_contentDiveLogsStore_3.value = listOf(shop_itemList[0],shop_itemList[1],shop_itemList[2],"baliDiveShop.jpg",
                                        shop_itemList[3],shop_itemList[4],shop_itemList[5],shop_itemList[6])
                                    dataModelComent.list_item_contentDiveLogsStore_4.value = listOf(shop_itemList[0],shop_itemList[1],shop_itemList[2],"ScubaDubaDooDiveCentr.jpg",
                                        shop_itemList[3],shop_itemList[4],shop_itemList[5],shop_itemList[6])

                                    chetchik_image_shop++
                                    if(chetchik_image_shop >= 4){chetchik_image_shop=0}

                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.i("TAG", "loadPost:onCancelled", error.toException())
                            }

                        }
                        databaseShop.addListenerForSingleValueEvent(valueShop)


                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }
            databaseStore.addListenerForSingleValueEvent(valueStore)






        }

        getFireData(this)

        dataModelComent.list_storesName.value = store_itemListName
        dataModelComent.list_storesLocation.value = store_itemListLocation
        dataModelComent.list_storesRental.value = store_itemListRental
        dataModelComent.list_storesDescription.value = store_itemListDescription
        dataModelComent.list_storesOcenka.value = store_itemListOcenka
        dataModelComent.list_storesOcenkaUser.value = store_itemListOcenkaUser
        dataModelComent.list_storesNumber.value = store_itemListNumberStore


        fun getKolichestvoContents(listener: ContentAdapter.Listener){
            databaseFireing = FirebaseDatabase.getInstance().getReference("contents")
        }
        getKolichestvoContents(this)
        fun getTagsActLoc(){

            for (numContent in 1..3){
                databaseFireing = FirebaseDatabase.getInstance().getReference("contents").child("content_$numContent").child("coments").child("tags")
                val valueTags = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var listValTags = ArrayList<String>()
                        for(valTags in snapshot.children){
                            Log.e("INFO", "Данные полученные с Tags: $valTags")

                            listValTags.add(valTags.value.toString())
                            if(listValTags.size == 3 && numContent == 1){
                                dataModelComent.map_search_content_1.value = mapOf("Content_${numContent.toString()}" to listOf<String>(numContent.toString(), listValTags[0],
                                                                                                                                        listValTags[1], listValTags[2]))
                            }
                            if(listValTags.size == 3 && numContent == 2){
                                dataModelComent.map_search_content_2.value = mapOf("Content_${numContent.toString()}" to listOf<String>(numContent.toString(), listValTags[0],
                                    listValTags[1], listValTags[2]))
                            }
                            if(listValTags.size == 3 && numContent == 3){
                                dataModelComent.map_search_content_3.value = mapOf("Content_${numContent.toString()}" to listOf<String>(numContent.toString(), listValTags[0],
                                    listValTags[1], listValTags[2]))
                            }


                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.i("TAG", "loadPost:onCancelled", error.toException())
                    }

                }
                databaseFireing.addListenerForSingleValueEvent(valueTags)
            }
        }
        getTagsActLoc()




        return inflatere
    }

    private fun getFire(fireDatabaseInstante: FirebaseDatabase,one_text_tage: String, two_text_tags: String, three_text_tags: String): DatabaseReference {
        val result = fireDatabaseInstante.getReference(one_text_tage).child(two_text_tags).child(three_text_tags)
        return result
    }



    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment5()
    }

    override fun onClick(getDataContent: GetDataContent) {
        val perevod_full_content = ContentAdapter.ContentHolder(requireView())
        dataModel.item_tema_message.value = getDataContent.title
        dataModel.item_level_diving.value = getDataContent.level_diving
        dataModel.item_ocenka.value = getDataContent.ocenka
        dataModel.item_ocenka_user.value = getDataContent.ocenka_user
        dataModel.item_location.value = getDataContent.location
        dataModel.item_description.value = getDataContent.description
        dataModel.item_image.value = getDataContent.image
        dataModel.item_numberContent.value = getDataContent.numberContent
        dataModel.item_numberContent.observe(this, {
            dataModelComent.message_numberContent.value = it
        })
        dataModel.item_ocenka.observe(this, {
            dataModelComent.item_ocenka.value = it
        })
        perevod_open_content = true
        dataModel.message_full_content.value = perevod_open_content
        dataModel.item_tema_message.observe(this, {
        })
        dataModelComent.list_storesLocation.observe(this, {
        })
        dataModelComent.signal_main.value = true
        dataModelComent.signal_seeAll.value = false
        dataModelComent.message_startContent.value = true
        dataModelComent.message_back_diveLogs.value = false
        dataModel.message_back_from_openItem_diveLogs.value = false

        dataModelComent.number_visite.observe(this, {
            databaseUserVisite.setValue((it.toInt()+1).toString())
        })




    }

    override fun onClick(getDataStore: GetDataStore) {

        dataModelComent.item_nameStore.value = getDataStore.name_store
        dataModelComent.item_locationStore.value = getDataStore.name_store
        dataModelComent.item_rentalStore.value = getDataStore.rental_store
        dataModelComent.item_imageStore.value = getDataStore.imageStore
        dataModelComent.item_descriptionStore.value = getDataStore.description
        dataModelComent.item_ocenkaStore.value = getDataStore.ocenka
        dataModelComent.item_ocenkaUserStore.value = getDataStore.ocenka_user
        dataModelComent.item_ocenkaNumberStore.value = getDataStore.numberStore


        dataModelComent.message_openItem_store.value = true
        dataModelComent.signal_main.value = true
        dataModelComent.message_startContent.value = false

        dataModelComent.number_visite.observe(this, {
            databaseUserVisite.setValue((it.toInt()+1).toString())
        })



    }


}