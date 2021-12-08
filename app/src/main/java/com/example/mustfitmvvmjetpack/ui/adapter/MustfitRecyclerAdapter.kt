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
        val currentItem = informationList[position]
        holder.binding.apply {
            tvName.text = currentItem.name
            tvBodyFat.text = currentItem.bodyFat.toString()
            tvDealWeight.text = currentItem.dealWeight.toString()
            tvBodyFatMessage.text = currentItem.messageBodyFat
            tvDailyCalorie.text = currentItem.messageCalorie
            tvLoseGainMessage.text = currentItem.messageLoseGainWeight
        }
    }

    override fun getItemCount(): Int {
        return informationList.size
    }

    class InformationHolder(val binding: ProfileItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

}