package com.example.recordedproject.utils

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
open class Event<out T> (private val content: @RawValue T) : Parcelable{

    @IgnoredOnParcel
    var hasBeenHandled = false
    private set


    fun getContentIfNotHandled():T?{
        return if (hasBeenHandled){
            null
        }
        else{
            hasBeenHandled = true
            content
        }

    }


}