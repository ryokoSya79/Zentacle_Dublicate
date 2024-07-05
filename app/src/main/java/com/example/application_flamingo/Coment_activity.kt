package com.example.application_flamingo

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatatypeMismatchException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Coment_activity : AppCompatActivity() {
    private val dataModel: DataModel by viewModels()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coment)

        val rc_view_coment: RecyclerView = findViewById(R.id.rc_view_coments)
        rc_view_coment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dataModel.message.observe(this, {
        })
        var adaptaris = ComentAdapter()
        rc_view_coment.adapter = adaptaris
    }

}