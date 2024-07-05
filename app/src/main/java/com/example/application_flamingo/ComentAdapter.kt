package com.example.application_flamingo

import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ComentAdapter: RecyclerView.Adapter<ComentAdapter.ComentHolder>(){

    val comentList = ArrayList<GetDataComent>()

    class ComentHolder(item: View): RecyclerView.ViewHolder(item){
        val name_coment: TextView = item.findViewById(R.id.name_coments)
        val coment: TextView = item.findViewById(R.id.coments)
        val kol_stars: TextView = item.findViewById(R.id.kol_stars)
        val star_1: ImageView = item.findViewById(R.id.star_1)
        val star_2: ImageView = item.findViewById(R.id.star_2)
        val star_3: ImageView = item.findViewById(R.id.star_3)
        val star_4: ImageView = item.findViewById(R.id.star_4)
        val star_5: ImageView = item.findViewById(R.id.star_5)

        fun bind(getDataComent: GetDataComent){
            name_coment.text = getDataComent.name
            coment.text = getDataComent.coment
            kol_stars.text = getDataComent.kol_stars


            if(kol_stars.text.toString() == "1"){
                star_1.setImageResource(R.drawable.ic_ocenka_image)
            }
            if(kol_stars.text.toString() == "2"){
                star_1.setImageResource(R.drawable.ic_ocenka_image)
                star_2.setImageResource(R.drawable.ic_ocenka_image)
            }
            if(kol_stars.text.toString() == "3"){
                star_1.setImageResource(R.drawable.ic_ocenka_image)
                star_2.setImageResource(R.drawable.ic_ocenka_image)
                star_3.setImageResource(R.drawable.ic_ocenka_image)
            }
            if(kol_stars.text.toString() == "4"){
                star_1.setImageResource(R.drawable.ic_ocenka_image)
                star_2.setImageResource(R.drawable.ic_ocenka_image)
                star_3.setImageResource(R.drawable.ic_ocenka_image)
                star_4.setImageResource(R.drawable.ic_ocenka_image)
            }
            if(kol_stars.text.toString() == "5"){
                star_1.setImageResource(R.drawable.ic_ocenka_image)
                star_2.setImageResource(R.drawable.ic_ocenka_image)
                star_3.setImageResource(R.drawable.ic_ocenka_image)
                star_4.setImageResource(R.drawable.ic_ocenka_image)
                star_5.setImageResource(R.drawable.ic_ocenka_image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coment, parent, false)
        return ComentHolder(view)
    }

    override fun getItemCount(): Int {
        return comentList.size
    }

    override fun onBindViewHolder(holder: ComentHolder, position: Int) {
        holder.bind(comentList[position])
    }

    fun addComent(getDataComent: GetDataComent){
        comentList.add(getDataComent)
        notifyDataSetChanged()
    }


}