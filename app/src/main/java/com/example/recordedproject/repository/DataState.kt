package com.example.recordedproject.repository

sealed class DataState<out T> {
    data class Success<out T>(val data:T) : DataState<T>()


    object Loading : DataState<Nothing>()




}