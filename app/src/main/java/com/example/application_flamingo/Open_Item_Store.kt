package com.example.application_flamingo

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
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


class Open_Item_Store : Fragment() {
    lateinit var text_to_title: TextView
    lateinit var text_location: TextView
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
    lateinit var descriptionStore: TextView
    lateinit var ocenkaStore: TextView
    lateinit var ocenkaUserStore: TextView
    lateinit var ocenkaStoreBig: TextView
    lateinit var kolComentStore: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatere = inflater.inflate(R.layout.fragment_open__item__store, container, false)

        text_to_title = inflatere.findViewById(R.id.text_tema_openItem)
        text_location = inflatere.findViewById(R.id.location_openItem)
        image = inflatere.findViewById(R.id.image_openItem)
        descriptionStore = inflatere.findViewById(R.id.description_openItemStore)
        ocenkaStore = inflatere.findViewById(R.id.text_ocenka_openItemStore)
        ocenkaStoreBig = inflatere.findViewById(R.id.text_ocenka_openItem_bigStore)
        ocenkaUserStore = inflatere.findViewById(R.id.text_kol_ocenok_openItemStore)
        kolComentStore = inflatere.findViewById(R.id.kol_comentStore)

        progressBar_1 = inflatere.findViewById(R.id.progressBar_1)
        progressBar_2 = inflatere.findViewById(R.id.progressBar_2)
        progressBar_3 = inflatere.findViewById(R.id.progressBar_3)
        progressBar_4 = inflatere.findViewById(R.id.progressBar_4)
        progressBar_5 = inflatere.findViewById(R.id.progressBar_5)



        val drawable_pb: Drawable = resources.getDrawable(R.drawable.gradient_to_progress_bar)




        val back_button: ImageView = inflatere.findViewById(R.id.back_button)
        back_button.setOnClickListener{
            dataModelComent.signal_main.observe(this,{it_main ->
                    if(it_main){
                        dataModel.message_back_from_openItem.value = true
                        dataModel.message_back_from_openItem_search.value = false
                    }
                    else{
                        dataModel.message_back_from_openItem_search.value = true
                    }
            })

        }

        test_fun(drawable_pb)

        return inflatere
    }


    companion object {
        @JvmStatic
        fun newInstance() = Open_Item_Store()
    }

    fun test_fun(drawable_pb: Drawable){
        dataModelComent.item_nameStore.observe(this, {
            text_to_title.text = it
        })
        dataModelComent.item_locationStore.observe(this, {
            text_location.text = it
        })
        dataModelComent.item_imageStore.observe(this, {
            val storageStore = Firebase.storage
            val imageReferenceStore = storageStore.getReferenceFromUrl("gs://flamingotest-1b056.appspot.com/StoreImage/${it}")
            imageReferenceStore.downloadUrl.addOnSuccessListener {
                Glide.with(this).load(it).into(image)
            }
        })
        dataModelComent.item_descriptionStore.observe(this, {
            descriptionStore.text = it
        })
        dataModelComent.item_ocenkaStore.observe(this, {
            ocenkaStore.text = it
            ocenkaStoreBig.text = it
        })
        dataModelComent.item_ocenkaUserStore.observe(this, {
            ocenkaUserStore.text = it
            val value_start = it.replace("(", "")
            val value_finish = value_start.replace(")", "")
        })
        dataModelComent.item_ocenkaNumberStore.observe(this, {
            if(it == "1"){
                progressBar_1.max = 20
                progressBar_2.max = 20
                progressBar_3.max = 20
                progressBar_4.max = 20
                progressBar_5.max = 20

                progressBar_1.progress = 1
                progressBar_2.progress = 3
                progressBar_3.progress = 1
                progressBar_4.progress =13
                progressBar_5.progress = 17

                kolComentStore.text = "35"
                ocenkaStore.text = "4.7"
            }
            if(it == "2"){
                progressBar_1.max = 20
                progressBar_2.max = 20
                progressBar_3.max = 20
                progressBar_4.max = 20
                progressBar_5.max = 20

                progressBar_1.progress = 8
                progressBar_2.progress = 3
                progressBar_3.progress = 20
                progressBar_4.progress = 11
                progressBar_5.progress = 12

                kolComentStore.text = "57"
                ocenkaStore.text = "3.4"
            }
            if(it == "3"){
                progressBar_1.max = 20
                progressBar_2.max = 20
                progressBar_3.max = 20
                progressBar_4.max = 20
                progressBar_5.max = 20

                progressBar_1.progress = 15
                progressBar_2.progress = 19
                progressBar_3.progress = 1
                progressBar_4.progress = 2
                progressBar_5.progress = 4

                kolComentStore.text = "41"
                ocenkaStore.text = "2.2"
            }
            if(it == "4"){
                progressBar_1.max = 20
                progressBar_2.max = 20
                progressBar_3.max = 20
                progressBar_4.max = 20
                progressBar_5.max = 20

                progressBar_1.progress = 5
                progressBar_2.progress = 11
                progressBar_3.progress = 18
                progressBar_4.progress = 20
                progressBar_5.progress = 16

                kolComentStore.text = "70"
                ocenkaStore.text = "4.8"

            }
        })


    }

}