package com.example.application_flamingo

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.application_flamingo.db.DbManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Profile_Fragment : Fragment(){

    val dataModelComent: DataModelComent by activityViewModels()
    val dataModel: DataModel by activityViewModels()
    lateinit var databaseUserVisite: DatabaseReference
    lateinit var avatarProfile: ImageView
    lateinit var databaseImage: DatabaseReference
    lateinit var databaseAboutMe: DatabaseReference
    lateinit var databaseClue: DatabaseReference
    private val REQUEST_CODE = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatere = inflater.inflate(R.layout.fragment_profile, container, false)
        var name_profile: TextView = inflatere.findViewById(R.id.name_profile)
        val visite: TextView = inflatere.findViewById(R.id.number_visite)
        val diveLogs: TextView = inflatere.findViewById(R.id.number_diveLogs)
        var edit_text_about_me: EditText = inflatere.findViewById(R.id.editTextText)
        var corect_button: ImageView = inflatere.findViewById(R.id.corect_button)
        val connect_err: ImageView = inflatere.findViewById(R.id.connect_errProfile)
        val clue_avatar: ImageView = inflatere.findViewById(R.id.clue_avatar)
        val clue_clickSave: ImageView = inflatere.findViewById(R.id.clue_click_save)
        val anim_connect_err: Animation = AnimationUtils.loadAnimation(context, R.anim.connect_err)
        val anim_visible: Animation = AnimationUtils.loadAnimation(context, R.anim.clue_visible)
        val anim_onvisible: Animation = AnimationUtils.loadAnimation(context, R.anim.clue_onvisible)
        val anim_visible_logout: Animation = AnimationUtils.loadAnimation(context, R.anim.log_out_visible)
        val anim_onvisible_logout: Animation = AnimationUtils.loadAnimation(context, R.anim.log_out_onvisible)

        val NetworkManager = NetworkManager()
        val button_menu_logout: ImageView = inflatere.findViewById(R.id.button_menu_logout)
        val button_logout: ImageView = inflatere.findViewById(R.id.button_logout)
        var chetchik_three_button = 0


        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)


        avatarProfile = inflatere.findViewById(R.id.avatarProfile)




        val dbManager = DbManager(requireContext())
        dbManager.openDb()
        var dataList = dbManager.readDbData()
        dataModelComent.clue_proverka.observe(this, {
            if(it){
                val dbManager = DbManager(requireContext())
                dbManager.openDb()
                dbManager.insertToDb( dataList[4].toString(), dataList[5].toString().toInt(), dataList[6].toString(), 1)
                dbManager.closeDb()
            }
        })
        button_logout.setVisibility(View.GONE)
        if(dataList.size > 10){
            if(dataList[11] == 1){
                clue_avatar.translationX = -800.0f
                clue_clickSave.translationX = 500.0f
            }
        }else{
            if(dataList[7] == 1){
                clue_avatar.translationX = -800.0f
                clue_clickSave.translationX = 500.0f
            }
        }
        dbManager.closeDb()

        button_menu_logout.setOnClickListener {
            chetchik_three_button++
            if(chetchik_three_button == 1){
                button_menu_logout.setImageResource(R.drawable.ic_three_button_press)
                button_logout.startAnimation(anim_visible_logout)
                button_logout.setVisibility(View.INVISIBLE)
            }
            if(chetchik_three_button == 2){
                chetchik_three_button = 0
                button_menu_logout.setImageResource(R.drawable.ic_three_button)
                button_logout.startAnimation(anim_onvisible_logout)
                button_logout.setVisibility(View.GONE)

            }
        }
        button_logout.setOnClickListener {
            val dbManager = DbManager(requireContext())
            dbManager.openDb()
            dbManager.allDelete()
            dbManager.closeDb()
            dataModel.message_logout.value = true
        }

        avatarProfile.setOnClickListener {
            val dbManager = DbManager(requireContext())
            dbManager.openDb()
            var dataList = dbManager.readDbData()

            if(dataList.size > 11){
                if(dataList[11] == 0){
                    clue_avatar.startAnimation(anim_onvisible)
                    clue_clickSave.startAnimation(anim_onvisible)
                }
            }else{
                if(dataList[7] == 0){
                    clue_avatar.startAnimation(anim_onvisible)
                    clue_clickSave.startAnimation(anim_onvisible)
                }
            }
            dbManager.closeDb()
            dataModelComent.clue_proverka.value = true
            databaseClue.setValue(true)
        }


        if (NetworkManager.isNetworkAvailable(requireContext())) {

            databaseClue = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("clue")
            val valueClue = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val clueData = snapshot.value.toString()
                    if(clueData == "true") {
                        dataModelComent.clue_proverka.value = true

                        clue_avatar.startAnimation(anim_onvisible)
                        clue_clickSave.startAnimation(anim_onvisible)

                        clue_avatar.setVisibility(View.INVISIBLE)
                        clue_clickSave.setVisibility(View.INVISIBLE)

                    }else{
                        clue_avatar.setVisibility(View.GONE)
                        clue_clickSave.setVisibility(View.GONE)

                        clue_avatar.startAnimation(anim_visible)
                        clue_clickSave.startAnimation(anim_visible)

                        clue_avatar.setVisibility(View.VISIBLE)
                        clue_clickSave.setVisibility(View.VISIBLE)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }
            databaseClue.addListenerForSingleValueEvent(valueClue)

            databaseUserVisite = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("visite_site")
            val valueUserVisite = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val visiteUser = snapshot.value.toString()
                    visite.text = visiteUser

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }
            databaseUserVisite.addListenerForSingleValueEvent(valueUserVisite)

            databaseAboutMe = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("about_me")
            val valueDatabaseAboutMe = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var about_me_text = snapshot.value.toString()
                    edit_text_about_me.setText(edit_text_about_me.text.toString()+about_me_text.toString())

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }



            databaseAboutMe.addListenerForSingleValueEvent(valueDatabaseAboutMe)


            databaseImage = FirebaseDatabase.getInstance().getReference("users").child("${dataList[6]}").child("avatar_image")
            val valueDatabaseImage = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var avatarImage = snapshot.value.toString()
                    if(avatarImage != "image"){
                        avatarProfile.setImageURI(avatarImage.toUri())
                        Glide.with(context!!).load(avatarImage).into(avatarProfile)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }
            databaseImage.addListenerForSingleValueEvent(valueDatabaseImage)

        } else {
            connect_err.startAnimation(anim_connect_err)
            avatarProfile.setImageResource(R.drawable.avatar_image_profile)
        }


        corect_button.setOnTouchListener(object : View.OnTouchListener {

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        databaseAboutMe.setValue(edit_text_about_me.text.toString())
                        val dbManager = DbManager(requireContext())
                        dbManager.openDb()

                        var dataList = dbManager.readDbData()
                        if(dataList.size > 11){
                            if(dataList[11] == 0){
                                clue_avatar.startAnimation(anim_onvisible)
                                clue_clickSave.startAnimation(anim_onvisible)
                            }
                        }else{
                            if(dataList[7] == 0){
                                clue_avatar.startAnimation(anim_onvisible)
                                clue_clickSave.startAnimation(anim_onvisible)
                            }
                        }

                        dbManager.insertToDb( dataList[4].toString(), dataList[5].toString().toInt(), dataList[6].toString(), 1)
                        dbManager.closeDb()
                        databaseClue.setValue(true)

                        corect_button.setImageResource(R.drawable.ic_corect_about_me_press)
                    }
                    MotionEvent.ACTION_MOVE -> {
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        corect_button.setImageResource(R.drawable.ic_corect_about_me)
                    }
                }
                return true
            }
        })

        dbManager.closeDb()


        dataModelComent.name_sqlite.observe(this, {
            name_profile.text = it
        })

        dataModelComent.number_diveLogs.observe(this, {
            diveLogs.text = it
        })


        dataModelComent.item_image.observe(this, {
            if(it != null){
                databaseImage.setValue(it)
            }
        })



        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = Profile_Fragment()
    }


}