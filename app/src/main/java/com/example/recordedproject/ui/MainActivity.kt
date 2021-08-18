package com.example.recordedproject.ui
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recordedproject.modelClasses.ColorResponse
import com.example.recordedproject.R
import com.example.recordedproject.adapter.ColorListAdapters
import com.example.recordedproject.databinding.ActivityMainBinding
import com.example.recordedproject.repository.DataState
import com.example.recordedproject.viewModel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val mainActivityViewModel :MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setObservable()
    }

    override fun onResume() {
        super.onResume()
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
                    else -> Unit
                }
            }
        })
    }


    private fun bindResult(data : ArrayList<ColorResponse>){
        activityMainBinding.apply {
            activityMainBinding.colorAdapter = ColorListAdapters()
            colorAdapter?.addItem(data)

        }
    }

}