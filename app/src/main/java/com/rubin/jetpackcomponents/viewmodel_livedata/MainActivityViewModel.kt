package com.rubin.jetpackcomponents.viewmodel_livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainActivityViewModel : ViewModel() {

    private lateinit var myRandomNumber: MutableLiveData<String>

    fun getNumber(): MutableLiveData<String> {
        Log.i(TAG, "Get Number")

        if (!::myRandomNumber.isInitialized) {
            myRandomNumber = MutableLiveData()
            this.createNumber()
        }
        return myRandomNumber
    }

    fun createNumber() {
        Log.i(TAG, "Create new Number")
        val random = Random()
        myRandomNumber.value = "Number: ${(random.nextInt(10 - 1) + 1)}"
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "ViewModel Destroyed")
    }

    companion object {
        private val TAG = MainActivityViewModel::class.java.simpleName
    }
}