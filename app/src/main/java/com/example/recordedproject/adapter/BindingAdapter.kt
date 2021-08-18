package com.example.recordedproject.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("loadProfileImage")
fun loadProfileImage(imageView: ImageView,imageUrl : String){
    imageUrl.let { Picasso.get().load(imageUrl).into(imageView) }
}