package com.example.tomtomexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tomtomexample.databinding.LocationRowItemBinding
import com.example.tomtomexample.response.Result

class LandingFragmentAdapter(private var itemList:List<Result>,var onItemClick: (Result)->Unit = {} ):RecyclerView.Adapter<LandingFragmentAdapter.tomtomHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tomtomHolder {
        val binding=LocationRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return tomtomHolder(binding)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: tomtomHolder, position: Int) {
        holder.bind(itemList[position])
        if(itemList[position].poi?.url==null){
            holder.binding.Linker.visibility= View.INVISIBLE
        }
        else
        holder.binding.Linker.setOnClickListener {  onItemClick(itemList[position])}
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    fun updateData(newData: List<Result>) {
        // Update the adapter's data and notify the change
        itemList = newData
        notifyDataSetChanged()
    }
    class tomtomHolder( var binding: LocationRowItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:Result){
            binding.Name.text=item.poi?.name?:"EMPTY"
            binding.Address.text=item.address?.freeformAddress?:"EMPTY"
               val bundle = Bundle().apply {
                    putString("name", item.poi?.name?:"Missing")
                    putDouble("lat", item.position.lat)
                    putDouble("lon",item.position.lon)
                }
                binding.rowList.setOnClickListener{
                    Navigation.findNavController(it).navigate(R.id.toMap,bundle)
                }

        }
    }
}