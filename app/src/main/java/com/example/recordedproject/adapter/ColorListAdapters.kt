package com.example.recordedproject.adapter

import com.example.recordedproject.ColorResponse.ColorResponseObject
import com.example.recordedproject.R
import com.example.recordedproject.databinding.ImageRowBinding

class ColorListAdapters  : BaseRecyclerView<ColorResponseObject,ImageRowBinding> (){
    override fun getLayout() = R.layout.image_row


    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ImageRowBinding>,
        position: Int
    ) {
        holder.binding.colorResponseObject = item[position]
    }


}



