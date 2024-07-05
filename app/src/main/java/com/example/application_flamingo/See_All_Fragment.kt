package com.example.application_flamingo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class See_All_Fragment : Fragment(), ContentAdapter.Listener {
    lateinit var rc_view: RecyclerView
    lateinit var content_itemList: ArrayList<String>
    lateinit var content_image: ArrayList<String>
    lateinit var databaseFireing: DatabaseReference
    private val dataModel: DataModel by activityViewModels()
    private val dataModelComent: DataModelComent by activityViewModels()
    var perevod_open_content = false

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
        val inflatere = inflater.inflate(R.layout.fragment_see__all_, container, false)
        val adapteris_for_rc = ContentAdapter(this)
        val button_back: ImageView = inflatere.findViewById(R.id.back_button_seeAll)
        rc_view = inflatere.findViewById(R.id.rc_view_see_all)

        rc_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rc_view.adapter = adapteris_for_rc

        button_back.setOnClickListener {
            dataModel.message_back_from_openItem.value = true
        }


        fun getFireData(listener: ContentAdapter.Listener){
            databaseFireing = FirebaseDatabase.getInstance().getReference("contents")
            val valueList = object : ValueEventListener {
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



                            adapteris.addContent(GetDataContent(content_itemList[0],
                                content_itemList[1],content_itemList[2],content_itemList[3],content_itemList[4],
                                content_image[chetchik_image], content_itemList[5], content_itemList[6]))
                            chetchik_image++
                            if(chetchik_image >= 3){chetchik_image=0}

                        }




                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }
            }
            databaseFireing.addListenerForSingleValueEvent(valueList)

        }

        getFireData(this)


        return inflatere

    }

    companion object {
        @JvmStatic
        fun newInstance() = See_All_Fragment()
    }

    override fun onClick(getDataContent: GetDataContent) {
        Toast.makeText(context, "КАРТИНКА БЫЛА НАЖАТА ${getDataContent.title}", Toast.LENGTH_LONG).show()

        val perevod_full_content = ContentAdapter.ContentHolder(requireView())
        dataModel.item_tema_message.value = getDataContent.title
        dataModel.item_level_diving.value = getDataContent.level_diving
        dataModel.item_ocenka.value = getDataContent.ocenka
        dataModel.item_ocenka_user.value = getDataContent.ocenka_user
        dataModel.item_location.value = getDataContent.location
        dataModel.item_description.value = getDataContent.description
        dataModel.item_image.value = getDataContent.image
        dataModel.item_numberContent.value = getDataContent.numberContent

        perevod_open_content = true
        dataModel.message_full_content.value = perevod_open_content
        dataModelComent.signal_main.value = true
    }
}