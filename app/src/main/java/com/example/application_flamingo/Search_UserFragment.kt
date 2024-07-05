package com.example.application_flamingo

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.application_flamingo.db.DbManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Search_UserFragment : Fragment(), SearchUserAdapter.Listener{
    var perevod_open_content = false
    private val dataModelComent: DataModelComent by activityViewModels()
    private val dataModelNew: NewModelView by activityViewModels()
    private val dataModel: DataModel by activityViewModels()
    lateinit var databaseUserVisite: DatabaseReference


    var sovpadenie_to_1 = false
    var sovpadenie_to_2 = false
    var sovpadenie_to_3 = false

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
        val inflatere = inflater.inflate(R.layout.fragment_search__user, container, false)
        val rc_view_result: RecyclerView = inflatere.findViewById(R.id.rc_view_search_result)

        var list_content_1 = ArrayList<String>()
        var list_content_2 = ArrayList<String>()
        var list_content_3 = ArrayList<String>()
        var search_edit_textUser: EditText = inflatere.findViewById(R.id.search_edit_text_user)
        val connect_err: ImageView = inflatere.findViewById(R.id.connect_errSearchUser)
        val anim_connect_err: Animation = AnimationUtils.loadAnimation(context, R.anim.connect_err)
        val NetworkManager = NetworkManager()

        val dbManager = DbManager(requireContext())
        dbManager.openDb()
        var dataList = dbManager.readDbData()

        if (NetworkManager.isNetworkAvailable(requireContext())) {
        } else {
            connect_err.startAnimation(anim_connect_err)
        }

        databaseUserVisite = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("visite_site")
        val valueUserVisite = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val visiteUser = snapshot.value.toString()
                dataModelComent.number_visite.value = visiteUser

            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("TAG", "loadPost:onCancelled", error.toException())
            }

        }
        databaseUserVisite.addListenerForSingleValueEvent(valueUserVisite)
        dbManager.closeDb()


        dataModelComent.item_content_number_1.observe(this, {
            list_content_1.add(it["numberContent"].toString())
            list_content_1.add(it["title"].toString())
            list_content_1.add(it["location"].toString())
            list_content_1.add(it["ocenka"].toString())
            list_content_1.add(it["ocenka_user"].toString())
            list_content_1.add(it["level_diving"].toString())
            list_content_1.add(it["description"].toString())
            list_content_1.add(it["image"].toString())


        })

        dataModelComent.item_content_number_2.observe(this, {
            list_content_2.add(it["numberContent"].toString())
            list_content_2.add(it["title"].toString())
            list_content_2.add(it["location"].toString())
            list_content_2.add(it["ocenka"].toString())
            list_content_2.add(it["ocenka_user"].toString())
            list_content_2.add(it["level_diving"].toString())
            list_content_2.add(it["description"].toString())
            list_content_2.add(it["image"].toString())

        })

        dataModelComent.item_content_number_3.observe(this, {
            list_content_3.add(it["numberContent"].toString())
            list_content_3.add(it["title"].toString())
            list_content_3.add(it["location"].toString())
            list_content_3.add(it["ocenka"].toString())
            list_content_3.add(it["ocenka_user"].toString())
            list_content_3.add(it["level_diving"].toString())
            list_content_3.add(it["description"].toString())
            list_content_3.add(it["image"].toString())


        })




        rc_view_result.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rc_view_result.setNestedScrollingEnabled(false)

        var adapreis = SearchUserAdapter(this)
        rc_view_result.adapter = adapreis



        dataModelComent.textSearch.observe(this, {
            search_edit_textUser.setText(search_edit_textUser.text.toString()+it)
            search_edit_textUser.setSelection(search_edit_textUser.text.length)
        })



        var param1 = false
        var param2 = false
        var param3 = false


        fun sovpadenie(sovpadenie_1: Boolean, sovpadenie_2: Boolean, sovpadenie_3: Boolean) {
            var in_chetchik_povtoreniy = 0

            if (sovpadenie_1==true || sovpadenie_2==true || sovpadenie_3==true) {
                adapreis.contentList = ArrayList()
                if (sovpadenie_1==true) {
                    adapreis.addContent(
                        GetDataSearchUser(
                            list_content_1[5], list_content_1[4],
                            list_content_1[2], list_content_1[1],
                            list_content_1[3], list_content_3[7],
                            list_content_1[6], list_content_1[0]
                        )
                    )


                }
                if (sovpadenie_2==true) {
                    adapreis.addContent(
                        GetDataSearchUser(
                            list_content_2[5], list_content_2[4],
                            list_content_2[2], list_content_2[1],
                            list_content_2[3], list_content_1[7],
                            list_content_2[6], list_content_2[0]
                        )
                    )
                }
                if (sovpadenie_3==true) {
                    adapreis.addContent(
                        GetDataSearchUser(
                            list_content_3[5], list_content_3[4],
                            list_content_3[2], list_content_3[1],
                            list_content_3[3], list_content_2[7],
                            list_content_3[6], list_content_3[0]
                        )
                    )
                }
                if(!sovpadenie_1 && !sovpadenie_to_2 && !sovpadenie_3){
                    adapreis.contentList = ArrayList()
                }
                sovpadenie_to_1 = false
                sovpadenie_to_2 = false
                sovpadenie_to_3 = false

            }


        }


        dataModelComent.signal_content_1.observe(this, {
            sovpadenie_to_1 = false
            Log.e("KOK", "Content_1 ${it}")
            if(it){
                sovpadenie_to_1 = true
                adapreis.addContent(
                    GetDataSearchUser(
                        list_content_1[5], list_content_1[4],
                        list_content_1[2], list_content_1[1],
                        list_content_1[3], list_content_3[7],
                        list_content_1[6], list_content_1[0]
                    )
                )
            }
            param1 = true
        })
        dataModelComent.signal_content_2.observe(this, {
            sovpadenie_to_2 = false
            Log.e("KOK", "Content_1 ${it}")
            if(it){
                sovpadenie_to_2 = true
                adapreis.addContent(
                    GetDataSearchUser(
                        list_content_2[5], list_content_2[4],
                        list_content_2[2], list_content_2[1],
                        list_content_2[3], list_content_1[7],
                        list_content_2[6], list_content_2[0]
                    )
                )
            }
            param2 = true
        })
        dataModelComent.signal_content_3.observe(this, {
            sovpadenie_to_3 = false
            Log.e("KOK", "Content_1 ${it}")
            if(it){
                sovpadenie_to_3 = true
                adapreis.addContent(
                    GetDataSearchUser(
                        list_content_3[5], list_content_3[4],
                        list_content_3[2], list_content_3[1],
                        list_content_3[3], list_content_2[7],
                        list_content_3[6], list_content_3[0]
                    )
                )
            }
            param3 = true
        })

        search_edit_textUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                adapreis.contentList = ArrayList()
            }

            override fun afterTextChanged(s: Editable) {
                if (s.toString() == "") {
                    dataModelComent.signal_content_1.value = false
                    dataModelComent.signal_content_2.value = false
                    dataModelComent.signal_content_3.value = false
                    dataModelComent.message_empty_text.value = true
                }


            }
        })
        search_edit_textUser.requestFocus()



        return inflatere
    }

    companion object {

        @JvmStatic
        fun newInstance() = Search_UserFragment()
    }


    override fun onClickUserSearch(getDataSearchUsers: GetDataSearchUser) {
        Toast.makeText(
            context,
            "КАРТИНКА БЫЛА НАЖАТА ${getDataSearchUsers.title}",
            Toast.LENGTH_LONG
        ).show()

        val perevod_full_content = ContentAdapter.ContentHolder(requireView())
        dataModel.item_tema_message.value = getDataSearchUsers.title
        dataModel.item_level_diving.value = getDataSearchUsers.level_diving
        dataModel.item_ocenka.value = getDataSearchUsers.ocenka
        dataModel.item_ocenka_user.value = getDataSearchUsers.ocenka_user
        dataModel.item_location.value = getDataSearchUsers.location
        dataModel.item_description.value = getDataSearchUsers.description
        dataModel.item_image.value = getDataSearchUsers.image
        dataModel.item_numberContent.value = getDataSearchUsers.numberContent
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

        dataModelComent.signal_main.value = false
        dataModelComent.signal_seeAll.value = false
        dataModel.message_back_from_openItem.value = false
        dataModelComent.message_back_diveLogs.value = false
        dataModel.message_back_from_openItem_diveLogs.value = false

        dataModelComent.number_visite.observe(this, {
            databaseUserVisite.setValue((it.toInt()+1).toString())
        })

    }

}