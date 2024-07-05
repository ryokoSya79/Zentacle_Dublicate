package com.example.application_flamingo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.application_flamingo.db.DbManager
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private val tf = supportFragmentManager
    private val dataModel: DataModel by viewModels()
    private var back_pressed: Long = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)


        val dbManager = DbManager(this)
        dbManager.openDb()
        var dataList = dbManager.readDbData()
        println("Сам список: $dataList и его размер: ${dataList.size}")

        if(dataList.size != 0 && dataList.size != 4){
            if(dataList[5].toString() == "1" ){
                val intent = Intent(this, Main_Application().javaClass)
                startActivity(intent)
            }
        }





        tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE).replace(R.id.frag, BlankFragment.newInstance()).commit()

        dataModel.message.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.frag, BlankFragment2.newInstance()).commit()

            }
        })
        dataModel.message_to_reg.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.frag, BlankFragment3.newInstance()).commit()
            }
        })

        dataModel.message_sign_in.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.frag, BlankFragment4.newInstance()).commit()
            }
        })
        dataModel.message_sign_up.observe(this,{
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.frag, BlankFragment3.newInstance()).commit()
            }
        })
        dataModel.message_skip.observe(this, {
            if(it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.frag, BlankFragment3.newInstance()).commit()
            }
        })
        dataModel.message_user_done.observe(this, {
            if(it){
                val intent = Intent(this, Main_Application().javaClass)
                startActivity(intent)
                finishAffinity()
            }
        })


        val bundle_act: Bundle? = intent.extras
        if(bundle_act?.get("log_out")=="true"){
            tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.frag, BlankFragment4.newInstance()).commit()
        }


    }


    override fun onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis())
            super.onBackPressed()
        else
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

}