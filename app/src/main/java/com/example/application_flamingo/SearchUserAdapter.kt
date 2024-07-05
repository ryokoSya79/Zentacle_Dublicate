package com.example.application_flamingo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class SearchUserAdapter(val listener: Listener): RecyclerView.Adapter<SearchUserAdapter.SearchUserHolder>(){

    var contentList = ArrayList<GetDataSearchUser>()

    class SearchUserHolder(item: View): RecyclerView.ViewHolder(item){


        val tema: TextView = item.findViewById(R.id.tema_diving)
        val location: TextView = item.findViewById(R.id.text_lovation)
        val level_dive: TextView = item.findViewById(R.id.level_user)
        val ocenka: TextView = item.findViewById(R.id.ocenka)
        val ocenkaUse: TextView = item.findViewById(R.id.number_user)
        val image: ImageView = item.findViewById(R.id.image_diving)
        val numberContent: TextView = item.findViewById(R.id.numberContetn)



        fun bind(getDataSearchUser: GetDataSearchUser, listener: Listener){
            tema.text = getDataSearchUser.title
            location.text = getDataSearchUser.location
            level_dive.text = getDataSearchUser.level_diving
            ocenka.text = getDataSearchUser.ocenka
            ocenkaUse.text = getDataSearchUser.ocenka_user
            numberContent.text = getDataSearchUser.numberContent



            val storage = Firebase.storage
            val storageRef = storage.reference
            val gsReference = storage.getReferenceFromUrl("gs://flamingotest-1b056.appspot.com/image/${getDataSearchUser.image}")
            gsReference.downloadUrl.addOnSuccessListener {
                Glide.with(itemView.context).load(it).into(image)
            }

            image.setOnClickListener {
                listener.onClickUserSearch(getDataSearchUser)
            }
            tema.setOnClickListener{
                listener.onClickUserSearch(getDataSearchUser)
            }
            location.setOnClickListener{
                listener.onClickUserSearch(getDataSearchUser)
            }
            ocenka.setOnClickListener{
                listener.onClickUserSearch(getDataSearchUser)
            }
            ocenkaUse.setOnClickListener{
                listener.onClickUserSearch(getDataSearchUser)
            }
            level_dive.setOnClickListener{
                listener.onClickUserSearch(getDataSearchUser)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content, parent, false)
        return SearchUserHolder(view)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: SearchUserHolder, position: Int) {
        holder.bind(contentList[position], listener)
    }
    fun addContent(getDataSearchUser: GetDataSearchUser){
        contentList.add(getDataSearchUser)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClickUserSearch(getDataSearchUsers: GetDataSearchUser)
    }


}