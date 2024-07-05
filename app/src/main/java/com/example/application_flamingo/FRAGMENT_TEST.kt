package com.example.application_flamingo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FRAGMENT_TEST : Fragment() {
    val dataModelComent: DataModelComent by activityViewModels()
    val dataModel: DataModel by viewModels()
    lateinit var databaseFireComent: DatabaseReference
    lateinit var databaseComentOcenka_1: DatabaseReference
    lateinit var rc_viewComent: RecyclerView



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
        val inflaters = inflater.inflate(R.layout.fragment_f_r_a_g_m_e_n_t__t_e_s_t, container, false)
        var progressBar_1: ProgressBar = inflaters.findViewById(R.id.coment_progressBar_1)
        var progressBar_2: ProgressBar = inflaters.findViewById(R.id.coment_progressBar_2)
        var progressBar_3: ProgressBar = inflaters.findViewById(R.id.coment_progressBar_3)
        var progressBar_4: ProgressBar = inflaters.findViewById(R.id.coment_progressBar_4)
        var progressBar_5: ProgressBar = inflaters.findViewById(R.id.coment_progressBar_5)
        val coment_back_button: ImageView = inflaters.findViewById(R.id.back_button_coment)
        var coment_ocenka: TextView = inflaters.findViewById(R.id.text_ocenka_openItem_big_coment)

        coment_back_button.setOnClickListener {
            dataModelComent.message_back_from_coment.value = true

        }
        val text: TextView = inflaters.findViewById(R.id.kol_coment)
        rc_viewComent = inflaters.findViewById(R.id.recyclerViewComentShow)
        rc_viewComent.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        val adapterComent = ComentAdapter()
        rc_viewComent.adapter = adapterComent

        dataModelComent.item_ocenka.observe(this, {
            coment_ocenka.text = it
        })

        dataModelComent.message_numberContent.observe(this) {
            dataModelComent.col_coment.observe(this, { NumberComent ->
                text.text = NumberComent
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

                databaseFireComent =
                    FirebaseDatabase.getInstance().getReference("contents").child("content_$it")
                        .child("coments")
                val valueDatabaseComent = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (numberComent in 1..NumberComent.toInt()) {

                            for (some_tags in snapshot.children) {
                                if (some_tags.key == "coment_$numberComent") {
                                    databaseComentOcenka_1 = FirebaseDatabase.getInstance().getReference("contents")
                                            .child("content_$it").child("coments")
                                            .child("coment_$numberComent").child("kol_stars")

                                    val valueDatabaseComentOcenka = object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {


                                            if (snapshot.value.toString() == "1") {
                                                listStars1++
                                            }
                                            if (snapshot.value.toString() == "2") {
                                                listStars2++
                                            }
                                            if (snapshot.value.toString() == "3") {
                                                listStars3++
                                            }
                                            if (snapshot.value.toString() == "4") {
                                                listStars4++
                                            }
                                            if (snapshot.value.toString() == "5") {
                                                listStars5++
                                            }

                                            if (numberComent == NumberComent.toInt()) {
                                                if (listStars1 > listStars2 && listStars1 > listStars3 && listStars1 > listStars4 && listStars1 > listStars5) {
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
                                                if (listStars2 > listStars1 && listStars2 > listStars3 && listStars2 > listStars4 && listStars2 > listStars5) {
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

                                                    listStarsFT_1 = false
                                                }
                                                if (listStars3 > listStars2 && listStars3 > listStars1 && listStars3 > listStars4 && listStars3 > listStars5) {
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

                                                    listStarsFT_1 = false
                                                }
                                                if (listStars4 > listStars2 && listStars4 > listStars3 && listStars4 > listStars1 && listStars4 > listStars5) {
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

                                                    listStarsFT_1 = false
                                                }
                                                if (listStars5 > listStars2 && listStars5 > listStars3 && listStars5 > listStars4 && listStars5 > listStars1) {
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

                                                    listStarsFT_1 = false

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
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            Log.i("TAG", "loadPost:onCancelled", error.toException())
                                        }
                                    }
                                    databaseComentOcenka_1.addListenerForSingleValueEvent(valueDatabaseComentOcenka)




                                    val databaseComentRC = FirebaseDatabase.getInstance().getReference("contents")
                                        .child("content_$it").child("coments")
                                        .child("coment_$numberComent")
                                    val valueDatabaseRC = object : ValueEventListener{
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            var coment_itemList = arrayListOf<String>()

                                            for(coment_snapshot in snapshot.children) {
                                                coment_itemList.add(coment_snapshot.getValue().toString())
                                            }
                                            adapterComent.addComent(GetDataComent(coment_itemList[0], coment_itemList[1], coment_itemList[2]))
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            Log.i("TAG", "loadPost:onCancelled", error.toException())
                                        }
                                    }
                                    databaseComentRC.addListenerForSingleValueEvent(valueDatabaseRC)
                                }

                            }
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.i("TAG", "loadPost:onCancelled", error.toException())
                    }

                }


                databaseFireComent.addListenerForSingleValueEvent(valueDatabaseComent)

            })
        }

        return inflaters
    }

    companion object {
        @JvmStatic
        fun newInstance() = FRAGMENT_TEST()
    }
}