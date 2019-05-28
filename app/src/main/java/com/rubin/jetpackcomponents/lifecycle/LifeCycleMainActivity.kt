package com.rubin.jetpackcomponents.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rubin.jetpackcomponents.R

class LifeCycleMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_main)

        Log.i(TAG, "Owner onCreate")

        lifecycle.addObserver(MainActivityObserver())
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "Owner onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "Owner onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Owner onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Owner onDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "Owner onStop")
    }

    companion object {
        private val TAG: String = LifeCycleMainActivity::class.java.simpleName
    }
}