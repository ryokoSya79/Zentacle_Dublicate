package com.example.application_flamingo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.browser.customtabs.PostMessageBackend
import androidx.fragment.app.activityViewModels
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BlankFragment3 : Fragment() {

    private val dataModule: DataModel by activityViewModels()

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

        val inflatere = inflater.inflate(R.layout.fragment_blank3, container, false)

        val email: EditText = inflatere.findViewById(R.id.email)
        val pass: EditText = inflatere.findViewById(R.id.pass)
        val name: EditText = inflatere.findViewById(R.id.name_reg)
        val send_base: Button = inflatere.findViewById(R.id.sign_up)
        val text_sign_in: TextView = inflatere.findViewById(R.id.text_sign_in)
        val successfully: ImageView = inflatere.findViewById(R.id.successfully)
        val anim_successfully: Animation = AnimationUtils.loadAnimation(context, R.anim.connect_err)
        val anim_successfully_onvisible: Animation = AnimationUtils.loadAnimation(context, R.anim.connect_err_onvisible)


        var chetchik_user = 0




        val fragment = BlankFragment4()
        fragment.arguments?.putString("hello", "Gone")



        text_sign_in.setOnClickListener{
            dataModule.message_sign_in.value = true
        }


        val databaseUsers = FirebaseDatabase.getInstance().getReference("users")
        val valueUsers = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(value_users in snapshot.children){
                    chetchik_user++
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("TAG", "loadPost:onCancelled", error.toException())
            }

        }
        databaseUsers.addListenerForSingleValueEvent(valueUsers)

        send_base.setOnClickListener{

            chetchik_user++
            val addUserName = FirebaseDatabase.getInstance().getReference("users").child("user_${chetchik_user}").child("name")
            addUserName.setValue(name.text.toString())
            val addUserEmail = FirebaseDatabase.getInstance().getReference("users").child("user_${chetchik_user}").child("email")
            addUserEmail.setValue(email.text.toString())
            val addUserPass = FirebaseDatabase.getInstance().getReference("users").child("user_${chetchik_user}").child("pass")
            addUserPass.setValue(pass.text.toString())
            val addUserAboutMe = FirebaseDatabase.getInstance().getReference("users").child("user_${chetchik_user}").child("about_me")
            addUserAboutMe.setValue("About Me")
            val addUserDiveLogs = FirebaseDatabase.getInstance().getReference("users").child("user_${chetchik_user}").child("dive_logs")
            addUserDiveLogs.setValue("0")
            val addUserVisite = FirebaseDatabase.getInstance().getReference("users").child("user_${chetchik_user}").child("visite_site")
            addUserVisite.setValue("0")
            val addUserImageAvatar = FirebaseDatabase.getInstance().getReference("users").child("user_${chetchik_user}").child("avatar_image")
            val list_avatar = arrayListOf<String>("https://южные-регионы.рф/uploads/2023-12-18/shB8QoeX.jpg",
                                                                "https://images.mamaasia.info/content/uploads/2018/06/12230028/Diving_Samui9-1600x1067.jpg",
                                                                "https://sportishka.com/uploads/posts/2023-12/1702204831_sportishka-com-p-chernoe-more-daiving-oboi-80.jpg",
                                                                "https://static.vecteezy.com/system/resources/previews/013/146/758/original/frogman-scuba-diver-diving-png.png",
                                                                "https://get.pxhere.com/photo/recreation-diving-underwater-biology-freediving-sports-water-sport-scuba-diving-outdoor-recreation-marine-biology-underwater-diving-divemaster-1405957.jpg",
                                                                "https://www.funnyart.club/uploads/posts/2022-12/1672019049_www-funnyart-club-p-vodolaz-prikol-yumor-69.jpg",
                                                                "https://avatars.dzeninfra.ru/get-zen_doc/1532998/pub_635a77ebdbfff41fc1da7e6a_635a7818675d7076cf2c7515/scale_1200")
            val randomazer = (1..7).random()
            if(randomazer == 1){
                addUserImageAvatar.setValue("${list_avatar[0]}")
            }
            if(randomazer == 2){
                addUserImageAvatar.setValue("${list_avatar[1]}")
            }
            if(randomazer == 3){
                addUserImageAvatar.setValue("${list_avatar[2]}")
            }
            if(randomazer == 4){
                addUserImageAvatar.setValue("${list_avatar[3]}")
            }
            if(randomazer == 5){
                addUserImageAvatar.setValue("${list_avatar[4]}")
            }
            if(randomazer == 6){
                addUserImageAvatar.setValue("${list_avatar[5]}")
            }
            if(randomazer == 7){
                addUserImageAvatar.setValue("${list_avatar[6]}")
            }
            val addUserClue = FirebaseDatabase.getInstance().getReference("users").child("user_${chetchik_user}").child("clue")
            addUserClue.setValue("false")

            successfully.startAnimation(anim_successfully)
            Handler().postDelayed({
                successfully.startAnimation(anim_successfully_onvisible)
            }, 4000)
        }






        return inflatere

    }



    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment3()
    }
}