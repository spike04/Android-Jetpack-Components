package com.rubin.jetpackcomponents.data_binding

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rubin.jetpackcomponents.BR

class Contact(_name: String, _email: String) : BaseObservable() {

    @get:Bindable
    var name = _name
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var email = _email
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    companion object {
        @JvmStatic @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, imageUrl: String) {
            Glide.with(view.context)
                .load(imageUrl)
                .into(view)
        }
    }
}
