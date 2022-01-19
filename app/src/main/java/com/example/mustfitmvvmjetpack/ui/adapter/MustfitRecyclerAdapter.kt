package com.example.mustfitmvvmjetpack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mustfitmvvmjetpack.R
import com.example.mustfitmvvmjetpack.databinding.ProfileItemsBinding
import com.example.mustfitmvvmjetpack.model.MustfitModel

class MustfitRecyclerAdapter(private val informationList: ArrayList<MustfitModel>) :
    RecyclerView.Adapter<MustfitRecyclerAdapter.InformationHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InformationHolder {
        val binding = DataBindingUtil.inflate<ProfileItemsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.profile_items,
            parent,
            false
        )
        return InformationHolder(binding)
    }

    override fun onBindViewHolder(holder: InformationHolder, position: Int) {
        holder.binding.profile = informationList[position]
    }

    override fun getItemCount(): Int {
        return informationList.size
    }

    class InformationHolder(val binding: ProfileItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

}