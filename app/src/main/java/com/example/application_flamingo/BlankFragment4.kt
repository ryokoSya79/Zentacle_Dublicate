package com.example.application_flamingo

import android.annotation.SuppressLint
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
import androidx.appcompat.widget.ThemedSpinnerAdapter.Helper
import androidx.core.os.bundleOf
import androidx.core.os.postDelayed
import androidx.fragment.app.activityViewModels
import com.example.application_flamingo.db.DbManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class BlankFragment4 : Fragment() {
    var prov_email = false
    var prov_pass = false


    val dataModel: DataModel by activityViewModels()
    val dataModelComent: DataModelComent by activityViewModels()
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
        val inflatere = inflater.inflate(R.layout.fragment_blank4, container, false)
        val btn_chekong: Button = inflatere.findViewById(R.id.sign_in_register)
        val email_reg: EditText = inflatere.findViewById(R.id.email_reg)
        val pass_reg: EditText = inflatere.findViewById(R.id.pass_reg)
        val text_sign_up: TextView = inflatere.findViewById(R.id.text_sign_up)
        val err_pass_email: ImageView = inflatere.findViewById(R.id.err_email_pass)
        val anim_err_pass_email: Animation = AnimationUtils.loadAnimation(context, R.anim.err_email_pass_visible)
        val anim_on_pass_email: Animation = AnimationUtils.loadAnimation(context, R.anim.err_email_pass_onvisible)

        text_sign_up.setOnClickListener{
            dataModel.message_sign_up.value = true
        }

        val dbManager = DbManager(requireContext())
        dbManager.openDb()
        dbManager.allDelete()
        dbManager.insertToDb("nameUser", 0, "user", 0)

        btn_chekong.setOnClickListener{

            val databaseUsers = FirebaseDatabase.getInstance().getReference("users")
            val valueUsers = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(users in snapshot.children){
                        val databaseUserEmail = FirebaseDatabase.getInstance().getReference("users").child("${users.key}").child("email")
                        val valueUserEmail = object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val emailUser = snapshot.value
                                if(emailUser == email_reg.getText().toString().toLowerCase().replace(" ", "")){
                                    prov_email = true
                                }else{
                                    prov_email = false
                                    err_pass_email.startAnimation(anim_err_pass_email)
                                    Handler().postDelayed({
                                        err_pass_email.startAnimation(anim_on_pass_email)
                                    }, 3000)
                                }
                            }
                            override fun onCancelled(error: DatabaseError) {
                                Log.i("TAG", "loadPost:onCancelled", error.toException())
                            }

                        }
                        databaseUserEmail.addListenerForSingleValueEvent(valueUserEmail)
                        val databaseUserPass = FirebaseDatabase.getInstance().getReference("users").child("${users.key}").child("pass")
                        val valueUserPass = object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val passUser = snapshot.value
                                if(pass_reg.text.toString() == passUser){
                                    prov_pass = true
                                }else{
                                    prov_pass = false
                                }


                            }
                            override fun onCancelled(error: DatabaseError) {
                                Log.i("TAG", "loadPost:onCancelled", error.toException())
                            }

                        }
                        databaseUserPass.addListenerForSingleValueEvent(valueUserPass)

                        val databaseUserName = FirebaseDatabase.getInstance().getReference("users").child("${users.key}").child("name")
                        val valueUserName = object : ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val nameUser = snapshot.value.toString()

                                if (prov_email && prov_pass){
                                    dbManager.insertToDb(nameUser, 1, users.key.toString(), 0)
                                    dataModel.message_user_done.value = true
                                }else{
                                    err_pass_email.startAnimation(anim_err_pass_email)
                                    Handler().postDelayed({
                                        err_pass_email.startAnimation(anim_on_pass_email)
                                    }, 3000)

                                }


                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.i("TAG", "loadPost:onCancelled", error.toException())
                            }

                        }
                        databaseUserName.addListenerForSingleValueEvent(valueUserName)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("TAG", "loadPost:onCancelled", error.toException())
                }

            }
            databaseUsers.addListenerForSingleValueEvent(valueUsers)

        }

        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment4()
    }
}