package com.example.recordedproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerView<T : Any, viewBinding :ViewDataBinding> : RecyclerView.Adapter<BaseRecyclerView.Companion.BaseViewHolder<viewBinding>>(){

    var item = mutableListOf<T>()

    @SuppressLint("Notify Dataset change")
    fun addItem(items : List<T>){
        this.item = items as MutableList<T>
        notifyDataSetChanged()
    }

    var listener : ((view: View, item :T,position : Int )-> Unit) ? = null

    abstract fun getLayout(): Int

    override fun getItemCount() = item.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder<viewBinding>(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),getLayout(),parent,false
        )

    )



   companion object{
       class BaseViewHolder<ViewBind : ViewDataBinding>(val binding: ViewBind):
               RecyclerView.ViewHolder(binding.root)

   }

}