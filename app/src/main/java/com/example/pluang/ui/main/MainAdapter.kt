package com.example.pluang.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pluang.R
import com.example.pluang.data.response.Item

class MainAdapter  : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    interface ClickListener{
        fun onItemClick(view: View,position: Int,list : ArrayList<Item> )
    }

     var list : ArrayList<Item> = ArrayList()
    private lateinit var clickListener: ClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recylerview_items,parent,false)
        return MainViewHolder(v,clickListener)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Glide.with(holder.image.context).load(list[position].avatars[0]).into(holder.image)
        holder.repoName.text = list[position].repo
        holder.repoDescription.text = list[position].desc

        ViewCompat.setTransitionName(holder.image,list[position].repo)
        ViewCompat.setTransitionName(holder.repoName,list[position].desc)
        ViewCompat.setTransitionName(holder.repoDescription,list[position].repo_link)
        holder.card?.setOnClickListener {
            clickListener.onItemClick(holder.itemView,position,list)
        }
    }


    fun submitList(itemList : ArrayList<Item>){
        list = itemList
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    class MainViewHolder(view : View,clickListener: ClickListener) : RecyclerView.ViewHolder(view)
    {
            val repoName = view.findViewById<TextView>(R.id.repo_name)!!
            val repoDescription = view.findViewById<TextView>(R.id.repo_description)!!
            val image = view.findViewById<ImageView>(R.id.profile_image)!!
            val card: CardView? = view.findViewById<CardView>(R.id.card)

        }


}