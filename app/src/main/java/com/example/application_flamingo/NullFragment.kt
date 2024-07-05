package com.example.application_flamingo

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.fragment.app.activityViewModels
import com.example.application_flamingo.db.DbManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NullFragment : Fragment() {

    private val dataModel: DataModel by activityViewModels()
    private val dataModelComent: DataModelComent by activityViewModels()
    var button_block = false
    lateinit var databaseUserDiveLogs: DatabaseReference
    var dataDiveLogs = 0




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

        val inflatere =  inflater.inflate(R.layout.fragment_null, container, false)


        var button_add_dive: Button = inflatere.findViewById(R.id.button_add_divee)
        var text_to_col_ocenok: TextView = inflatere.findViewById(R.id.text_kol_ocenok_2_openItem)
        var button_add_dive_close: Button = inflatere.findViewById(R.id.button_add_divee_close)
        var button_add_dive_close_ft = false
        val NetworkManager = NetworkManager()


        val dbManager = DbManager(requireContext())
        dbManager.openDb()
        var dataList = dbManager.readDbData()

        if (NetworkManager.isNetworkAvailable(requireContext())) {
            databaseUserDiveLogs = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("dive_logs")
            val valueUserVisite = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val diveLogsUser = snapshot.value.toString()
                    dataDiveLogs = diveLogsUser.toInt()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }
            databaseUserDiveLogs.addListenerForSingleValueEvent(valueUserVisite)
            dbManager.closeDb()


            dataModel.item_ocenka_user.observe(this, {
                val value_start = it.replace("(", "")
                val value_finish = value_start.replace(")", "")
                text_to_col_ocenok.text = value_finish
            })

            dataModelComent.item_ocenkaUserStore.observe(this, {
                val value_start = it.replace("(", "")
                val value_finish = value_start.replace(")", "")
                text_to_col_ocenok.text = value_finish
            })

            dataModel.item_numberContent.observe(this, {itContent ->
                if(itContent.toInt() == 1){
                    dataModelComent.item_button_block_logDive_1.observe(this, {
                        button_block = it
                        if(it){
                            button_add_dive.setBackgroundResource(R.drawable.gradient_close)
                        }
                    })
                }
                if(itContent.toInt() == 2){
                    dataModelComent.item_button_block_logDive_2.observe(this, {
                        button_block = it
                        if(it){
                            button_add_dive.setBackgroundResource(R.drawable.gradient_close)
                        }
                    })
                }
                if(itContent.toInt() == 3){
                    dataModelComent.item_button_block_logDive_3.observe(this, {
                        button_block = it
                        if(it){
                            button_add_dive.setBackgroundResource(R.drawable.gradient_close)
                        }
                    })
                }

            })

            dataModelComent.item_ocenkaNumberStore.observe(this, {itContent ->
                if(itContent.toInt() == 1){
                    dataModelComent.item_button_block_logDive_store_1.observe(this, {
                        button_block = it
                        if(it){
                            button_add_dive.setBackgroundResource(R.drawable.gradient_close)
                        }
                    })
                }
                if(itContent.toInt() == 2){
                    dataModelComent.item_button_block_logDive_store_2.observe(this, {
                        button_block = it
                        if(it){
                            button_add_dive.setBackgroundResource(R.drawable.gradient_close)
                        }
                    })
                }
                if(itContent.toInt() == 3){
                    dataModelComent.item_button_block_logDive_store_3.observe(this, {
                        button_block = it
                        if(it){
                            button_add_dive.setBackgroundResource(R.drawable.gradient_close)
                        }
                    })
                }
                if(itContent.toInt() == 4){
                    dataModelComent.item_button_block_logDive_store_4.observe(this, {
                        button_block = it
                        if(it){
                            button_add_dive.setBackgroundResource(R.drawable.gradient_close)
                        }
                    })
                }

            })
        } else {

        }






        button_add_dive.setOnClickListener{
            button_add_dive_close_ft = true
            dataModelComent.message_startContent.observe(this,{it_main ->
                if(!button_block) {
                    if (it_main) {
                        text_to_col_ocenok.text =
                            toInteger(text_to_col_ocenok.text.toString()).toString()


                        databaseUserDiveLogs.setValue((dataDiveLogs+1).toString())

                        databaseUserDiveLogs = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("dive_logs")
                        val valueUserVisite = object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val diveLogsUser = snapshot.value.toString()

                                dataDiveLogs = diveLogsUser.toInt()
                                dataModelComent.number_diveLogs.value = dataDiveLogs.toString()
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.i("TAG", "loadPost:onCancelled", error.toException())
                            }

                        }
                        databaseUserDiveLogs.addListenerForSingleValueEvent(valueUserVisite)

                        dataModel.item_numberContent.observe(this, {
                            val fireDatabase_to_KolOcenok =
                                FirebaseDatabase.getInstance().getReference("contents")
                                    .child("content_$it").child("ocenka_user")
                            fireDatabase_to_KolOcenok.setValue(text_to_col_ocenok.text.toString())
                            if(it.toInt()==1){
                                dataModelComent.item_button_block_logDive_1.value = true
                            }
                            if(it.toInt()==2){
                                dataModelComent.item_button_block_logDive_2.value = true
                            }
                            if(it.toInt()==3){
                                dataModelComent.item_button_block_logDive_3.value = true
                            }
                        })
                    } else {
                        text_to_col_ocenok.text =
                            toInteger(text_to_col_ocenok.text.toString()).toString()
                        dataModelComent.item_ocenkaNumberStore.observe(this, {
                            val fireDatabase_to_KolOcenokStore =
                                FirebaseDatabase.getInstance().getReference("shop_diving")
                                    .child("shope_$it").child("ocenka_user")
                            fireDatabase_to_KolOcenokStore.setValue(text_to_col_ocenok.text.toString())
                            if(it.toInt()==1){
                                dataModelComent.item_button_block_logDive_store_1.value = true
                            }
                            if(it.toInt()==2){
                                dataModelComent.item_button_block_logDive_store_2.value = true
                            }
                            if(it.toInt()==3){
                                dataModelComent.item_button_block_logDive_store_3.value = true
                            }
                            if(it.toInt()==3){
                                dataModelComent.item_button_block_logDive_store_3.value = true
                            }
                            if(it.toInt()==4){
                                dataModelComent.item_button_block_logDive_store_4.value = true
                            }
                        })
                    }
                }else {
                    button_add_dive.setBackgroundResource(R.drawable.gradient)
                    button_block = false
                    button_add_dive_close_ft = false
                    if (it_main) {
                        text_to_col_ocenok.text =
                            toIntegerAnti(text_to_col_ocenok.text.toString()).toString()


                        databaseUserDiveLogs.setValue((dataDiveLogs-1).toString())
                        databaseUserDiveLogs = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("dive_logs")
                        val valueUserVisite = object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val diveLogsUser = snapshot.value.toString()

                                dataDiveLogs = diveLogsUser.toInt()
                                dataModelComent.number_diveLogs.value = dataDiveLogs.toString()
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.i("TAG", "loadPost:onCancelled", error.toException())
                            }

                        }
                        databaseUserDiveLogs.addListenerForSingleValueEvent(valueUserVisite)


                        dataModel.item_numberContent.observe(this, {
                            val fireDatabase_to_KolOcenok =
                                FirebaseDatabase.getInstance().getReference("contents")
                                    .child("content_$it").child("ocenka_user")
                            fireDatabase_to_KolOcenok.setValue(text_to_col_ocenok.text.toString())

                            if(it.toInt()==1){
                                dataModelComent.item_button_block_logDive_1.value = false
                            }
                            if(it.toInt()==2){
                                dataModelComent.item_button_block_logDive_2.value = false
                            }
                            if(it.toInt()==3){
                                dataModelComent.item_button_block_logDive_3.value = false
                            }
                        })
                    } else {
                        text_to_col_ocenok.text =
                            toIntegerAnti(text_to_col_ocenok.text.toString()).toString()



                        dataModelComent.item_ocenkaNumberStore.observe(this, {
                            val fireDatabase_to_KolOcenokStore =
                                FirebaseDatabase.getInstance().getReference("shop_diving")
                                    .child("shope_$it").child("ocenka_user")
                            fireDatabase_to_KolOcenokStore.setValue(text_to_col_ocenok.text.toString())
                            if(it.toInt()==1){
                                dataModelComent.item_button_block_logDive_store_1.value = false
                            }
                            if(it.toInt()==2){
                                dataModelComent.item_button_block_logDive_store_2.value = false
                            }
                            if(it.toInt()==3){
                                dataModelComent.item_button_block_logDive_store_3.value = false
                            }
                            if(it.toInt()==4){
                                dataModelComent.item_button_block_logDive_store_4.value = false
                            }
                        })
                    }
                }
            })
            if(button_add_dive_close_ft){
                button_block = true
                button_add_dive.setBackgroundResource(R.drawable.gradient_close)
            }
        }


        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = NullFragment()
    }

    fun toInteger(s: String): Comparable<*> {
        val value_start = s.replace("(  ", "")
        val value_finish = value_start.replace(")", "")
        var value:Int? = value_finish.toIntOrNull()
        value = value!!+1
        val valoo = 0
        if (value != null) {
            return value.toUInt()
        }
        else {
        }
        return valoo
    }
    fun toIntegerAnti(s: String): Comparable<*> {
        val value_start = s.replace("(", "")
        val value_finish = value_start.replace(")", "")
        var value:Int? = value_finish.toIntOrNull()
        value = value!!-1
        val valoo = 0
        if (value != null) {
            return value.toUInt()
        }
        else {
        }

        return valoo
    }
}