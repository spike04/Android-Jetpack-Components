package com.rubin.jetpackcomponents.viewmodel_livedata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rubin.jetpackcomponents.R
import kotlinx.android.synthetic.main.activity_viewmodel_main.*

class ViewModelMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel_main)

        val model = ViewModelProviders.of(this@ViewModelMainActivity)
            .get(MainActivityViewModel::class.java)
        val myRandomNumber = model.getNumber()

        myRandomNumber.observe(this@ViewModelMainActivity, Observer { number ->
            txtNumber.text = number
            Log.i(TAG, "Random Number Set")
        })

        btnRandom.setOnClickListener {
            model.createNumber()
        }
    }

    companion object {
        private val TAG = ViewModelMainActivity::class.java.simpleName
    }
}