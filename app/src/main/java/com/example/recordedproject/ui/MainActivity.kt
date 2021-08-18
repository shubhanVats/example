package com.example.recordedproject.ui
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recordedproject.ColorResponse.ColorResponseObject
import com.example.recordedproject.R
import com.example.recordedproject.adapter.ColorListAdapters
import com.example.recordedproject.databinding.ActivityMainBinding
import com.example.recordedproject.repository.DataState
import com.example.recordedproject.viewModel.MainActivityViewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val mainActivityViewModel :MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setObservable()
        mainActivityViewModel.setStateEvent(MainActivityViewModel.MainEvent.ColorResponseItem)

    }

    private fun setObservable() {
        mainActivityViewModel.getColorResponseData.observe(this,{ event ->

            event.getContentIfNotHandled()?.let {
                dataState ->
                when(dataState){
                    is DataState.Success -> {
                        dataState.data.data?.let {
                            bindResult(it)
                        }
                    }

                }

            }


        })
    }


    private fun bindResult(data : ArrayList<ColorResponseObject>){
        activityMainBinding.apply {
            activityMainBinding.colorAdapter = ColorListAdapters()
            colorAdapter?.addItem(data)

        }
    }

}