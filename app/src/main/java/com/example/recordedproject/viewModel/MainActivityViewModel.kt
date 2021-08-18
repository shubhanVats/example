package com.example.recordedproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recordedproject.modelClasses.BaseResponse
import com.example.recordedproject.modelClasses.ColorResponse
import com.example.recordedproject.repository.DataState
import com.example.recordedproject.repository.MainRepo
import com.example.recordedproject.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(
    private val mainRepo: MainRepo
) : ViewModel() {

    val getColorResponseData: LiveData<Event<DataState<BaseResponse<ArrayList<ColorResponse>>>>>
    get() = _getColorResponse

    private val _getColorResponse : MutableLiveData<Event<DataState<BaseResponse<ArrayList<ColorResponse>>>>> =
        MutableLiveData()


    fun setStateEvent(mainEvent: MainEvent){
        viewModelScope.launch {
            when(mainEvent){
               is MainEvent.ColorResponseItem -> {
                   mainRepo.getColorResp().onEach {
                       dataState ->  _getColorResponse.value = Event(dataState)
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