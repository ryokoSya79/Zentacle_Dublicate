package com.example.application_flamingo

import android.location.GnssAntennaInfo.Listener
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.withContext

class ContentAdapter(val listener: Listener): RecyclerView.Adapter<ContentAdapter.ContentHolder>(){

    val contentList = ArrayList<GetDataContent>()

    class ContentHolder(item: View): RecyclerView.ViewHolder(item){


        val tema: TextView = item.findViewById(R.id.tema_diving)
        val location: TextView = item.findViewById(R.id.text_lovation)
        val level_dive: TextView = item.findViewById(R.id.level_user)
        val ocenka: TextView = item.findViewById(R.id.ocenka)
        val ocenkaUse: TextView = item.findViewById(R.id.number_user)
        val image: ImageView = item.findViewById(R.id.image_diving)
        val numberContent: TextView = item.findViewById(R.id.numberContetn)



        fun bind(getDataContent: GetDataContent, listener: Listener){
            tema.text = getDataContent.title
            location.text = getDataContent.location
            level_dive.text = getDataContent.level_diving
            ocenka.text = getDataContent.ocenka
            ocenkaUse.text = getDataContent.ocenka_user
            numberContent.text = getDataContent.numberContent



            val storage = Firebase.storage
            val storageRef = storage.reference
            val gsReference = storage.getReferenceFromUrl("gs://flamingotest-1b056.appspot.com/image/${getDataContent.image}")
            gsReference.downloadUrl.addOnSuccessListener {
                Glide.with(itemView.context).load(it).into(image)
            }
            image.setOnClickListener {
                listener.onClick(getDataContent)
            }
            tema.setOnClickListener{
                listener.onClick(getDataContent)
            }
            location.setOnClickListener{
                listener.onClick(getDataContent)
            }
            ocenka.setOnClickListener{
                listener.onClick(getDataContent)
            }
            ocenkaUse.setOnClickListener{
                listener.onClick(getDataContent)
            }
            level_dive.setOnClickListener{
                listener.onClick(getDataContent)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content, parent, false)
        return ContentHolder(view)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.bind(contentList[position], listener)
    }
    fun addContent(getDataContents: GetDataContent){
        contentList.add(getDataContents)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(getDataContent: GetDataContent)
    }

}