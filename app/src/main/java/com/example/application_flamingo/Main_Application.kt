package com.example.application_flamingo

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE


class Main_Application : AppCompatActivity() {
    private val tf = supportFragmentManager
    val dataModel: DataModel by viewModels()
    val dataModelComent: DataModelComent by viewModels()
    private var back_pressed: Long = 0
    private lateinit var pLauncher: ActivityResultLauncher<String>





    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_application)


        val frag_main: View = findViewById(R.id.frag_main)
        val arguments = intent.extras
        val user = arguments?.get("name")?.toString()

        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)


        if(savedInstanceState == null){
            tf.beginTransaction().replace(R.id.frag_main_menu, Main_menu()).commit()
        }

        tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).replace(R.id.frag_main, BlankFragment5.newInstance()).commit()

        val frag_menu: FrameLayout= findViewById(R.id.frag_main_menu)
        frag_menu.setBackgroundResource(R.drawable.round_lauout)

        dataModel.message_frag_home.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, BlankFragment5.newInstance()).commit()
            }
        })
        dataModel.message_see_all.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, See_All_Fragment.newInstance()).commit()
            }
        })
        dataModel.message_full_content.observe(this, {
            if (it) {
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.frag_main, Open_Item_Content.newInstance()).commit()
                val fragment_menu: Fragment? = tf.findFragmentById(R.id.frag_main_menu)
                if (fragment_menu != null && fragment_menu.isVisible) {
                    tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).hide(fragment_menu).commit()
                    tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main_menu, NullFragment.newInstance()).commit()

                }
            }


        })
        dataModel.message_back_from_openItem.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).replace(R.id.frag_main, BlankFragment5.newInstance()).commit()
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main_menu, Main_menu.newInstance()).commit()
            }
        })
        dataModel.message_back_from_openItem_search.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).replace(R.id.frag_main, Search_fragment.newInstance()).commit()
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main_menu, Main_menu.newInstance()).commit()
            }
        })
        dataModel.message_back_from_openItem_seeAll.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).replace(R.id.frag_main, See_All_Fragment.newInstance()).commit()
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main_menu, Main_menu.newInstance()).commit()
            }
        })
        dataModelComent.message_back_from_coment.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).replace(R.id.frag_main, Open_Item_Content.newInstance()).commit()
            }
        })

        dataModel.message_show_coments.observe(this, {
           if(it){
               tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, FRAGMENT_TEST.newInstance()).commit()
           }
        })
        dataModel.message_search.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, Search_fragment.newInstance()).commit()
            }
        })

        dataModelComent.signal_content_1.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, Search_UserFragment.newInstance()).commit()
            }
        })
        dataModelComent.signal_content_2.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, Search_UserFragment.newInstance()).commit()
            }
        })
        dataModelComent.signal_content_3.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, Search_UserFragment.newInstance()).commit()
            }
        })
        dataModelComent.message_empty_text.observe(this, {
            if (it){
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, Search_fragment.newInstance()).commit()
            }
        })
        dataModelComent.signal_button_dive.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, Search_UserFragment.newInstance()).commit()
            }
        })
        dataModelComent.signal_button_party.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, Search_UserFragment.newInstance()).commit()
            }
        })
        dataModelComent.message_openItem_store.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, Open_Item_Store.newInstance()).commit()
                val fragment_menu: Fragment? = tf.findFragmentById(R.id.frag_main_menu)
                if (fragment_menu != null && fragment_menu.isVisible) {
                    tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).hide(fragment_menu).commit()
                }
            }
        })
        dataModel.message_log_dive.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, DiveLogsFragment.newInstance()).commit()
            }
        })
        dataModel.message_profile.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(TRANSIT_FRAGMENT_FADE).replace(R.id.frag_main, Profile_Fragment.newInstance()).commit()
            }
        })
        dataModel.message_logout.observe(this, {
            if(it){
//                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.frag, BlankFragment5.newInstance()).commit()
                val intent = Intent(this, MainActivity().javaClass)
                intent.putExtra("log_out", "true")
                startActivity(intent)
                finishAffinity()
            }
        })

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }

        else{
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }

    }
}
