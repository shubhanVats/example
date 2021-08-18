package com.example.recordedproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recordedproject.ColorResponse.BaseResponse
import com.example.recordedproject.ColorResponse.ColorResponseObject
import com.example.recordedproject.repository.DataState
import com.example.recordedproject.repository.MainRepo
import com.example.recordedproject.utils.Event
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel  @Inject constructor(
    private val mainRepo: MainRepo
) : ViewModel() {

    val getColorResponseData: LiveData<Event<DataState<BaseResponse<ArrayList<ColorResponseObject>>>>>
    get() = getColorResponseData

    private val getColorResponse : MutableLiveData<Event<DataState<BaseResponse<ArrayList<ColorResponseObject>>>>> =
        MutableLiveData()


    fun setStateEvent(mainEvent: MainEvent){
        viewModelScope.launch {
            when(mainEvent){
               is MainEvent.ColorResponseItem -> {
                   mainRepo.getColorResp().onEach {
                       dataState ->  getColorResponse.value = Event(dataState)
                   }
                       .launchIn(viewModelScope)

               }

            }
        }
    }

    sealed class MainEvent{
        object ColorResponseItem : MainEvent()
    }


}