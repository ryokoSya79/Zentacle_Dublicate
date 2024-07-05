package com.example.application_flamingo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.activityViewModels


class Main_menu : Fragment() {

    private val modelData: DataModel by activityViewModels()
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
        val inflatere = inflater.inflate(R.layout.main_menu, container, false)
        val button_home: ImageView = inflatere.findViewById(R.id.image_home)
        val button_search: ImageView = inflatere.findViewById(R.id.image_centr)
        val button_logs: ImageView = inflatere.findViewById(R.id.image_col)
        val button_profile: ImageView = inflatere.findViewById(R.id.button_profile)

        button_home.setOnClickListener {
            modelData.message_frag_home.value = true
            button_home.setImageResource(R.drawable.ic_explore_press)
            button_search.setImageResource(R.drawable.ic_search)
            button_logs.setImageResource(R.drawable.ic_logs)
            button_profile.setImageResource(R.drawable.ic_profile)
        }
        button_search.setOnClickListener{
            modelData.message_search.value = true
            button_search.setImageResource(R.drawable.ic_search_press)
            button_home.setImageResource(R.drawable.ic_explore)
            button_logs.setImageResource(R.drawable.ic_logs)
            button_profile.setImageResource(R.drawable.ic_profile)
        }
        button_logs.setOnClickListener {
            modelData.message_log_dive.value = true
            button_logs.setImageResource(R.drawable.ic_logs_press)
            button_search.setImageResource(R.drawable.ic_search)
            button_home.setImageResource(R.drawable.ic_explore)
            button_profile.setImageResource(R.drawable.ic_profile)
        }
        button_profile.setOnClickListener {
            modelData.message_profile.value = true
            button_profile.setImageResource(R.drawable.ic_profile_press)
            button_search.setImageResource(R.drawable.ic_search)
            button_logs.setImageResource(R.drawable.ic_logs)
            button_home.setImageResource(R.drawable.ic_explore)
        }

        modelData.message_back_from_openItem_search.observe(this, {
            if(it){
                button_search.setImageResource(R.drawable.ic_search_press)
                button_home.setImageResource(R.drawable.ic_explore)
                button_logs.setImageResource(R.drawable.ic_logs)
                button_profile.setImageResource(R.drawable.ic_profile)
            }
            else{
                button_search.setImageResource(R.drawable.ic_search)
                button_home.setImageResource(R.drawable.ic_explore_press)
                button_logs.setImageResource(R.drawable.ic_logs)
                button_profile.setImageResource(R.drawable.ic_profile)
            }
        })
        modelData.message_back_from_openItem_diveLogs.observe(this, {
            if(it){
                button_search.setImageResource(R.drawable.ic_search)
                button_home.setImageResource(R.drawable.ic_explore)
                button_logs.setImageResource(R.drawable.ic_logs_press)
                button_profile.setImageResource(R.drawable.ic_profile)
            }
        })
        return inflatere

    }

    companion object {
        @JvmStatic
        fun newInstance() = Main_menu()
    }
}