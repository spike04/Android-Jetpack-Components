package com.rubin.jetpackcomponents.data_binding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rubin.jetpackcomponents.R
import com.rubin.jetpackcomponents.databinding.ActivityDatabindingMainBinding

class DataBindingMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDatabindingMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_databinding_main
        )

        binding.run {
            //            txtName.text = "Dan Brown"
//            txtEmail.text = "danbrown@gmail.com"

            contact = Contact("Captain Marvel", "caption@gmail.com")
            handler = EventHandler(this@DataBindingMainActivity)
            imageUrl = "https://i.redd.it/lhw4vp5yoy121.jpg"
        }
    }
}
