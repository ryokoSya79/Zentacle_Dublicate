package com.example.application_flamingo

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.os.DropBoxManager
import android.os.Handler
import android.provider.ContactsContract.CommonDataKinds.Identity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.application_flamingo.db.DbManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class BlankFragment : Fragment() {

    private val dataModule: DataModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @SuppressLint("ResourceType", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_blank, container, false)
        val btn: Button = inflate.findViewById(R.id.button3)
        val skip: Button = inflate.findViewById(R.id.skip_button)

        skip.setOnClickListener {
            dataModule.message_skip.value = true
        }
        btn.setOnClickListener{
            dataModule.message.value = true
        }
        dataModule.message_logout.observe(this, {
            if(it){
                dataModule.message_logout_from_aplication.value = true
            }
        })



        return inflate
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}