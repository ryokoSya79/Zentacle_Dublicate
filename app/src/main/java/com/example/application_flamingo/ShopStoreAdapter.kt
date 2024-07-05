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
import com.google.firebase.storage.storage

class ShopStoreAdapter(val listener: Listener): RecyclerView.Adapter<ShopStoreAdapter.ShopStoreHolder>() {

    val shopStoreList = ArrayList<GetDataStore>()

    class ShopStoreHolder(item: View): RecyclerView.ViewHolder(item){
        val name_store: TextView = item.findViewById(R.id.name_shop)
        val location_store: TextView = item.findViewById(R.id.location_shop)
        val rental_store: TextView = item.findViewById(R.id.rental_shop)
        val imageShop: ImageView = item.findViewById(R.id.image_shop)

        fun bind(getDataStore: GetDataStore, listener: Listener){
            name_store.text = getDataStore.name_store
            location_store.text = getDataStore.location_store
            rental_store.text = getDataStore.rental_store

            val storegeImageStore = Firebase.storage
            val gsReference = storegeImageStore.getReferenceFromUrl("gs://flamingotest-1b056.appspot.com/StoreImage/${getDataStore.imageStore}")
            gsReference.downloadUrl.addOnSuccessListener {
                Glide.with(itemView.context).load(it).into(imageShop)
                println("Начало работы Glide")
            }
            imageShop.setOnClickListener { listener.onClick(getDataStore) }
            name_store.setOnClickListener{listener.onClick(getDataStore)}
            location_store.setOnClickListener{listener.onClick(getDataStore)}
            rental_store.setOnClickListener{listener.onClick(getDataStore)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopStoreHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_dive_shop, parent, false)
        return ShopStoreHolder(view)
    }

    override fun getItemCount(): Int {
        return shopStoreList.size
    }

    override fun onBindViewHolder(holder: ShopStoreHolder, position: Int) {
        holder.bind(shopStoreList[position], listener)
    }
    fun addStore(getDataStore: GetDataStore){
        shopStoreList.add(getDataStore)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(getDataStore: GetDataStore)
    }
}