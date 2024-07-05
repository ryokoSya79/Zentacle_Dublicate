package com.example.application_flamingo

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.application_flamingo.db.DbManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DiveLogsFragment : Fragment(), ContentAdapterDiveLogs.Listener {

    val dataModelComent: DataModelComent by activityViewModels()
    val dataModel: DataModel by activityViewModels()
    var perevod_open_content = false
    var chetchik_1 = 0
    var chetchik_2 = 0
    var chetchik_3 = 0
    var content_1 = false
    var content_2 = false
    var content_3 = false
    lateinit var databaseUserVisite: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatere = inflater.inflate(R.layout.fragment_dive_logs, container, false)

        var rc_contentLogDive: RecyclerView = inflatere.findViewById(R.id.rc_viewDiveLogs)
        var adapterisLogDive = ContentAdapterDiveLogs(this)
        val connect_err: ImageView = inflatere.findViewById(R.id.connect_errDiveLogs)
        val anim_connect_err: Animation = AnimationUtils.loadAnimation(context, R.anim.connect_err)
        val NetworkManager = NetworkManager()
        rc_contentLogDive.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rc_contentLogDive.adapter = adapterisLogDive

        val dbManager = DbManager(requireContext())
        dbManager.openDb()
        var dataList = dbManager.readDbData()

        databaseUserVisite = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("visite_site")

        dbManager.closeDb()

        if (NetworkManager.isNetworkAvailable(requireContext())) {
            dataModelComent.item_button_block_logDive_1.observe(this, {itTF ->
                if(itTF){
                    dataModelComent.list_item_contentDiveLogs_1.observe(this, {
                        chetchik_1++
                        content_1 = true
                        if(chetchik_1 == 1){
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))
                        }else{
                            adapterisLogDive.contentList = ArrayList()
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))
                            chetchik_1 = 0
                        }
                        if(content_1 && content_3){
                            chetchik_3++
                        }
                    })
                }
            })
            dataModelComent.item_button_block_logDive_2.observe(this, {itTF ->
                if(itTF){
                    dataModelComent.list_item_contentDiveLogs_2.observe(this, {
                        chetchik_2++
                        content_2 = true
                        if(chetchik_2 == 1){
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))
                        }else{
                            adapterisLogDive.contentList = ArrayList()
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))
                        }

                        if(content_2 && content_1 && chetchik_2 == 2){
                            adapterisLogDive.contentList = ArrayList()
                            dataModelComent.list_item_contentDiveLogs_1.observe(this, {itDive_1 ->
                                adapterisLogDive.addContentDiveLogs(GetDataContent(itDive_1[0],itDive_1[1],itDive_1[2],itDive_1[3],itDive_1[4],itDive_1[5],itDive_1[6], itDive_1[7]))
                            })
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))
                        }

                        if(content_2 && content_3 && chetchik_2 == 2){
                            adapterisLogDive.contentList = ArrayList()
                            chetchik_3++
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))
                        }

                    })
                }
            })
            dataModelComent.item_button_block_logDive_3.observe(this, {itTF ->
                if(itTF){
                    dataModelComent.list_item_contentDiveLogs_3.observe(this, {
                        chetchik_3++
                        content_3 = true
                        if(chetchik_3 == 1){
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))

                        }else if(chetchik_3 ==2 ){
                            adapterisLogDive.contentList = ArrayList()
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))
                        }
                        if(chetchik_3 == 3){
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))
                        }
                        if(content_1 && content_3 && chetchik_3 == 3){
                            adapterisLogDive.contentList = ArrayList()
                            dataModelComent.list_item_contentDiveLogs_1.observe(this, {itDive_1 ->
                                adapterisLogDive.addContentDiveLogs(GetDataContent(itDive_1[0],itDive_1[1],itDive_1[2],itDive_1[3],itDive_1[4],itDive_1[5],itDive_1[6], itDive_1[7]))
                            })
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))
                        }
                        if(content_1 && content_2 && content_3 && chetchik_3 == 4){
                            adapterisLogDive.contentList = ArrayList()
                            dataModelComent.list_item_contentDiveLogs_1.observe(this, {itDive_1 ->
                                adapterisLogDive.addContentDiveLogs(GetDataContent(itDive_1[0],itDive_1[1],itDive_1[2],itDive_1[3],itDive_1[4],itDive_1[5],itDive_1[6], itDive_1[7]))
                            })
                            dataModelComent.list_item_contentDiveLogs_2.observe(this, {itDive_2 ->
                                adapterisLogDive.addContentDiveLogs(GetDataContent(itDive_2[0],itDive_2[1],itDive_2[2],itDive_2[3],itDive_2[4],itDive_2[5],itDive_2[6], itDive_2[7]))
                            })
                            adapterisLogDive.addContentDiveLogs(GetDataContent(it[0],it[1],it[2],it[3],it[4],it[5],it[6], it[7]))
                        }


                    })
                }
            })
        } else {
            connect_err.startAnimation(anim_connect_err)
        }







        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = DiveLogsFragment()
    }

    override fun onClickDiveLogs(getDataContent: GetDataContent) {
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
        dataModelComent.message_back_diveLogs.value = true
        dataModel.message_back_from_openItem_diveLogs.value = true

        dataModelComent.number_visite.observe(this, {
            databaseUserVisite.setValue((it.toInt()+1).toString())
        })

    }
}