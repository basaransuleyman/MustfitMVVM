package com.example.mustfitmvvmjetpack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mustfitmvvmjetpack.databinding.ProfileItemsBinding
import com.example.mustfitmvvmjetpack.model.MustfitModel

class MustfitRecyclerAdapter(private val informationList: ArrayList<MustfitModel>) :
    RecyclerView.Adapter<MustfitRecyclerAdapter.InformationHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InformationHolder {
        val binding = ProfileItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InformationHolder(binding)
    }

    override fun onBindViewHolder(holder: InformationHolder, position: Int) {
        holder.binding.tvName.text = informationList[position].name
        holder.binding.tvBodyFat.text = informationList[position].bodyFat.toString()
        holder.binding.tvDealWeight.text = informationList[position].dealWeight.toString()
        holder.binding.tvBodyFatMessage.text = informationList[position].messageBodyFat
        holder.binding.tvDailyCalorie.text = informationList[position].messageCalorie
        holder.binding.tvLoseGainMessage.text = informationList[position].messageLoseGainWeight
    }

    override fun getItemCount(): Int {
        return informationList.size
    }

    class InformationHolder(val binding: ProfileItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}