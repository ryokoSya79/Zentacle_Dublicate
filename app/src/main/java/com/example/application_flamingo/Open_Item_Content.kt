package com.example.application_flamingo

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.gms.common.wrappers.Wrappers.packageManager
import com.google.android.gms.maps.MapView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import javax.net.ssl.ManagerFactoryParameters


class Open_Item_Content : Fragment() {
    lateinit var text_to_title: TextView
    lateinit var text_location: TextView
    lateinit var text_level_diving: TextView
    lateinit var text_ocenka: TextView
    lateinit var text_ocenka_user: TextView
    lateinit var text_description: TextView
    lateinit var image: ImageView
    lateinit var text_to_col_ocenok_big: TextView
    lateinit var col_coment: TextView
    private val dataModel: DataModel by activityViewModels()
    private val dataModelComent: DataModelComent by activityViewModels()
    lateinit var progressBar_1: ProgressBar
    lateinit var progressBar_2: ProgressBar
    lateinit var progressBar_3: ProgressBar
    lateinit var progressBar_4: ProgressBar
    lateinit var progressBar_5: ProgressBar
    lateinit var nameComent: TextView
    lateinit var stars_1: ImageView
    lateinit var stars_2: ImageView
    lateinit var stars_3: ImageView
    lateinit var stars_4: ImageView
    lateinit var stars_5: ImageView
    lateinit var coment: TextView
    lateinit var show_button_coments: Button
    lateinit var fragment_coment: View

    lateinit var textAct_one: TextView
    lateinit var textAct_two: TextView
    lateinit var textAct_three: TextView

    lateinit var textEnt_one: TextView
    lateinit var textEnt_two: TextView

    lateinit var textTag_one: TextView
    lateinit var textTag_two: TextView
    lateinit var textTag_three: TextView
    val frag_5 = BlankFragment5()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatere = inflater.inflate(R.layout.fragment_open__item__content, container, false)

        text_to_title = inflatere.findViewById(R.id.text_tema_openItem)
        text_location = inflatere.findViewById(R.id.location_openItem)
        text_level_diving = inflatere.findViewById(R.id.level_diving_openItem)
        text_ocenka = inflatere.findViewById(R.id.text_ocenka_openItem)
        text_ocenka_user = inflatere.findViewById(R.id.text_kol_ocenok_openItem)
        text_description = inflatere.findViewById(R.id.description_openItem)
        image = inflatere.findViewById(R.id.image_openItem)
        nameComent = inflatere.findViewById(R.id.name_coment)


        text_to_col_ocenok_big = inflatere.findViewById(R.id.text_ocenka_openItem_big)
        col_coment = inflatere.findViewById(R.id.kol_coment)

        progressBar_1 = inflatere.findViewById(R.id.progressBar_1)
        progressBar_2 = inflatere.findViewById(R.id.progressBar_2)
        progressBar_3 = inflatere.findViewById(R.id.progressBar_3)
        progressBar_4 = inflatere.findViewById(R.id.progressBar_4)
        progressBar_5 = inflatere.findViewById(R.id.progressBar_5)

        stars_1 = inflatere.findViewById(R.id.stars_1)
        stars_2 = inflatere.findViewById(R.id.stars_2)
        stars_3 = inflatere.findViewById(R.id.stars_3)
        stars_4 = inflatere.findViewById(R.id.stars_4)
        stars_5 = inflatere.findViewById(R.id.stars_5)

        coment = inflatere.findViewById(R.id.coment_openItem)

        textAct_one = inflatere.findViewById(R.id.act_one)
        textAct_two = inflatere.findViewById(R.id.act_two)
        textAct_three = inflatere.findViewById(R.id.act_three)

        textEnt_one = inflatere.findViewById(R.id.ent_one)
        textEnt_two = inflatere.findViewById(R.id.ent_two)

        textTag_one = inflatere.findViewById(R.id.tag_one)
        textTag_two = inflatere.findViewById(R.id.tag_two)
        textTag_three = inflatere.findViewById(R.id.tag_three)

        val connect_err: ImageView = inflatere.findViewById(R.id.connect_errOpenItem)
        val anim_connect_err: Animation = AnimationUtils.loadAnimation(context, R.anim.connect_err)
        val NetworkManager = NetworkManager()


        val drawable_pb: Drawable = resources.getDrawable(R.drawable.gradient_to_progress_bar)

        if (NetworkManager.isNetworkAvailable(requireContext())) {
            test_fun(drawable_pb)
        } else {
            connect_err.startAnimation(anim_connect_err)
        }


        val back_button: ImageView = inflatere.findViewById(R.id.back_button)
        back_button.setOnClickListener{
            dataModelComent.signal_main.observe(this,{it_main ->
                dataModelComent.signal_seeAll.observe(this, {it_seeAll ->
                    if(it_main){
                        dataModel.message_back_from_openItem.value = true
                        dataModel.message_back_from_openItem_search.value = false
                    }
                    else{
                        dataModel.message_back_from_openItem_search.value = true
                        dataModel.message_log_dive.value = false
                    }
                })
            })
            dataModelComent.message_back_diveLogs.observe(this, {
                if(it){
                    dataModel.message_log_dive.value = true
                    dataModelComent.message_dubl_back_diveLogs.value = true
                    dataModelComent.message_prob_back.value = true
                }
            })

        }





        fragment_coment = inflatere.findViewById(R.id.fragment_coments)
        show_button_coments = inflatere.findViewById(R.id.show_all_reviews)

        val button_color: Shader = LinearGradient(
            250f,
            0f,
            700f,
            0f,
            intArrayOf(Color.parseColor("#951bfe"), Color.parseColor("#0B94EF")),
            floatArrayOf(0.0f, 15f),
            Shader.TileMode.CLAMP

        )
        show_button_coments.paint.setShader(button_color)
        show_button_coments.setOnClickListener{
            dataModel.message_show_coments.value = true
        }







        return inflatere
    }


    companion object {
        @JvmStatic
        fun newInstance() = Open_Item_Content()
    }

    fun test_fun(drawable_pb: Drawable){
        dataModel.item_tema_message.observe(this, {
            text_to_title.text = it
        })
        dataModel.item_location.observe(this, {
            text_location.text = it
        })
        dataModel.item_ocenka.observe(this, {
            text_ocenka.text = it
            text_to_col_ocenok_big.text = it
        })
        dataModel.item_ocenka_user.observe(this, {
            text_ocenka_user.text = it
            val value_start = it.replace("(", "")
            val value_finish = value_start.replace(")", "")

        })
        dataModel.item_level_diving.observe(this, {
            text_level_diving.text = it
        })
        dataModel.item_description.observe(this, {
            text_description.text = it
        })
        dataModel.item_image.observe(this, {
            val storage = Firebase.storage
            val imageReference = storage.getReferenceFromUrl("gs://flamingotest-1b056.appspot.com/image/${it}")
            imageReference.downloadUrl.addOnSuccessListener {
                Glide.with(this).load(it).into(image)
            }
        })
        dataModel.item_numberContent.observe(this, {


            val storageComents = FirebaseDatabase.getInstance().getReference("contents").child("content_$it").child("coments")
            val valueComents = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val post = snapshot.value.toString()
//                    for(intComent in post.)
                    var text1 = post.split("coment_")
                    var text2 = text1.size-1
                    dataModel.item_col_coment.value = text2.toString()
                    dataModelComent.col_coment.value = text2.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }
            storageComents.addListenerForSingleValueEvent(valueComents)
        })
        dataModel.item_col_coment.observe(this, {
            col_coment.text = it

            dataModel.item_numberContent.observe(this, {numContent->
                var listStars = ArrayList<String>()
                var FirstName = ArrayList<String>()

                var listStars1 = 0
                var listStars2 = 0
                var listStars3 = 0
                var listStars4 = 0
                var listStars5 = 0

                var listStarsFT_1 = true
                var listStarsFT_2 = true
                var listStarsFT_3 = true
                var listStarsFT_4 = true
                var listStarsFT_5 = true

                var schetchik_stars = 0
                var schetchik_coments = 0
                for(number_Coment in 1..it.toInt()){



                    val storageName_Coment = FirebaseDatabase.getInstance().getReference("contents")
                        .child("content_$numContent").child("coments").child("coment_$number_Coment").child("name")
                    val valueName_coment = object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val postName = snapshot.value.toString()
                            if(schetchik_stars == 2){
                                nameComent.text = postName

                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.i("TAG", "loadPost:onCancelled", error.toException())
                        }

                    }
                    storageName_Coment.addListenerForSingleValueEvent(valueName_coment)

                    var list_act = ArrayList<String>()
                    var list_ent = ArrayList<String>()
                    var list_tags = ArrayList<String>()

                    val storageActivity = FirebaseDatabase.getInstance().getReference("contents").child("content_$numContent").child("coments").child("activity")
                    val valueActivity = object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for(act in snapshot.children){
                                list_act.add(act.getValue().toString())
                            }
                            textAct_one.text = list_act[0]
                            textAct_two.text = list_act[1]
                            textAct_three.text = list_act[2]
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.i("TAG", "loadPost:onCancelled", error.toException())
                        }
                    }
                    storageActivity.addListenerForSingleValueEvent(valueActivity)

                    val storageEntry = FirebaseDatabase.getInstance().getReference("contents").child("content_$numContent").child("coments").child("entry")
                    val valueEntry = object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for(act in snapshot.children){
                                list_ent.add(act.getValue().toString())
                            }
                            textEnt_one.text = list_ent[0]
                            textEnt_two.text = list_ent[1]
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.i("TAG", "loadPost:onCancelled", error.toException())
                        }
                    }
                    storageEntry.addListenerForSingleValueEvent(valueEntry)

                    val storageTags = FirebaseDatabase.getInstance().getReference("contents").child("content_$numContent").child("coments").child("tags")
                    val valueTags = object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for(act in snapshot.children){
                                list_tags.add(act.getValue().toString())
                            }
                            textTag_one.text = list_tags[0]
                            textTag_two.text = list_tags[1]
                            textTag_three.text = list_tags[2]
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.i("TAG", "loadPost:onCancelled", error.toException())
                        }
                    }
                    storageTags.addListenerForSingleValueEvent(valueTags)




                    val getStarsFirebase = FirebaseDatabase.getInstance().getReference("contents")
                        .child("content_$numContent").child("coments").child("coment_$number_Coment").child("kol_stars")
                    val valueComent = object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            listStars.add(snapshot.value.toString())

                            if(schetchik_stars == 2){

                                if(snapshot.value.toString().toInt() == 1){
                                    stars_1.setImageResource(R.drawable.ic_ocenka_image)
                                }
                                if(snapshot.value.toString().toInt() == 2){
                                    stars_1.setImageResource(R.drawable.ic_ocenka_image)
                                    stars_2.setImageResource(R.drawable.ic_ocenka_image)
                                }
                                if(snapshot.value.toString().toInt() == 3){
                                    stars_1.setImageResource(R.drawable.ic_ocenka_image)
                                    stars_2.setImageResource(R.drawable.ic_ocenka_image)
                                    stars_3.setImageResource(R.drawable.ic_ocenka_image)
                                }
                                if(snapshot.value.toString().toInt() == 4){
                                    stars_1.setImageResource(R.drawable.ic_ocenka_image)
                                    stars_2.setImageResource(R.drawable.ic_ocenka_image)
                                    stars_3.setImageResource(R.drawable.ic_ocenka_image)
                                    stars_4.setImageResource(R.drawable.ic_ocenka_image)
                                }
                                if(snapshot.value.toString().toInt() == 5){
                                    stars_1.setImageResource(R.drawable.ic_ocenka_image)
                                    stars_2.setImageResource(R.drawable.ic_ocenka_image)
                                    stars_3.setImageResource(R.drawable.ic_ocenka_image)
                                    stars_4.setImageResource(R.drawable.ic_ocenka_image)
                                    stars_5.setImageResource(R.drawable.ic_ocenka_image)
                                }
                            }

                            if(snapshot.value.toString() == "1"){
                                listStars1++
                            }
                            if(snapshot.value.toString() == "2"){
                                listStars2++
                            }
                            if(snapshot.value.toString() == "3"){
                                listStars3++
                            }
                            if(snapshot.value.toString() == "4"){
                                listStars4++
                            }
                            if(snapshot.value.toString() == "5"){
                                listStars5++
                            }

                            if(number_Coment==it.toInt()) {
                                if(listStars1 > listStars2 && listStars1 > listStars3 && listStars1 > listStars4 && listStars1 > listStars5){
                                    progressBar_1.max = listStars1
                                    progressBar_2.max = listStars1
                                    progressBar_3.max = listStars1
                                    progressBar_4.max = listStars1
                                    progressBar_5.max = listStars1

                                    progressBar_1.progress = listStars1
                                    progressBar_2.progress = listStars2
                                    progressBar_3.progress = listStars3
                                    progressBar_4.progress = listStars4
                                    progressBar_5.progress = listStars5

                                    listStarsFT_1 = false
                                }
                                if(listStars2 > listStars1 && listStars2 > listStars3 && listStars2 > listStars4 && listStars2 > listStars5){
                                    progressBar_1.max = listStars2
                                    progressBar_2.max = listStars2
                                    progressBar_3.max = listStars2
                                    progressBar_4.max = listStars2
                                    progressBar_5.max = listStars2

                                    progressBar_1.progress = listStars1
                                    progressBar_2.progress = listStars2
                                    progressBar_3.progress = listStars3
                                    progressBar_4.progress = listStars4
                                    progressBar_5.progress = listStars5

                                    listStarsFT_2 = false
                                }
                                if(listStars3 > listStars2 && listStars3 > listStars1 && listStars3 > listStars4 && listStars3 > listStars5){
                                    progressBar_1.max = listStars3
                                    progressBar_2.max = listStars3
                                    progressBar_3.max = listStars3
                                    progressBar_4.max = listStars3
                                    progressBar_5.max = listStars3

                                    progressBar_1.progress = listStars1
                                    progressBar_2.progress = listStars2
                                    progressBar_3.progress = listStars3
                                    progressBar_4.progress = listStars4
                                    progressBar_5.progress = listStars5

                                    listStarsFT_3 = false
                                }
                                if(listStars4 > listStars2 && listStars4 > listStars3 && listStars4 > listStars1 && listStars4 > listStars5){
                                    progressBar_1.max = listStars4
                                    progressBar_2.max = listStars4
                                    progressBar_3.max = listStars4
                                    progressBar_4.max = listStars4
                                    progressBar_5.max = listStars4

                                    progressBar_1.progress = listStars1
                                    progressBar_2.progress = listStars2
                                    progressBar_3.progress = listStars3
                                    progressBar_4.progress = listStars4
                                    progressBar_5.progress = listStars5

                                    listStarsFT_4 = false
                                }
                                if(listStars5 > listStars2 && listStars5 > listStars3 && listStars5 > listStars4 && listStars5 > listStars1){
                                    progressBar_1.max = listStars5
                                    progressBar_2.max = listStars5
                                    progressBar_3.max = listStars5
                                    progressBar_4.max = listStars5
                                    progressBar_5.max = listStars5

                                    progressBar_1.progress = listStars1
                                    progressBar_2.progress = listStars2
                                    progressBar_3.progress = listStars3
                                    progressBar_4.progress = listStars4
                                    progressBar_5.progress = listStars5

                                    listStarsFT_5 = false
                                }

                                if(listStarsFT_1 && listStarsFT_2 && listStarsFT_3 && listStarsFT_4 && listStarsFT_5){
                                    progressBar_1.max = 5
                                    progressBar_2.max = 5
                                    progressBar_3.max = 5
                                    progressBar_4.max = 5
                                    progressBar_5.max = 5

                                    progressBar_1.progress = listStars1
                                    progressBar_2.progress = listStars2
                                    progressBar_3.progress = listStars3
                                    progressBar_4.progress = listStars4
                                    progressBar_5.progress = listStars5

                                }
                            }
                            schetchik_stars ++
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.i("TAG", "loadPost:onCancelled", error.toException())
                        }

                    }
                    getStarsFirebase.addListenerForSingleValueEvent(valueComent)

                    val storageComents = FirebaseDatabase.getInstance().getReference("contents")
                        .child("content_$numContent").child("coments").child("coment_$number_Coment").child("coment")
                    val valueStorageComent =  object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (schetchik_coments == 2){
                                var post = snapshot.value.toString()
                                coment.text = post
                            }
                            schetchik_coments++

                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.i("TAG", "loadPost:onCancelled", error.toException())
                        }

                    }
                    storageComents.addListenerForSingleValueEvent(valueStorageComent)
                }


            })


        })


    }

}