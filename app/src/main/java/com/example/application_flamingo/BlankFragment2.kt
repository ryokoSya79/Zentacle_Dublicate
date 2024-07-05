package com.example.application_flamingo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels


class BlankFragment2 : Fragment() {
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

        val inflatere = inflater.inflate(R.layout.fragment_blank2, container, false)
        val btn: Button = inflatere.findViewById(R.id.button3)
        val skip_button: Button = inflatere.findViewById(R.id.skip_button_2)

        skip_button.setOnClickListener{
            dataModule.message_skip.value = true
        }
        btn.setOnClickListener {
            dataModule.message_to_reg.value = true
        }


        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment2()

    }
}