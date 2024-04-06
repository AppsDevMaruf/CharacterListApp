package com.maruf.characterlistapp.utils.bindingAdapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.maruf.characterlistapp.R
import com.maruf.characterlistapp.utils.splitFullName
import java.util.Locale

@BindingAdapter("loadImageFromUrl")
fun ShapeableImageView.loadImageFromUrl(imageUrl: String) {
    load(imageUrl) {
        crossfade(600)
        placeholder(R.drawable.ic_error_placeholder)
        error(R.drawable.ic_error_placeholder)
    }
}

@BindingAdapter("setFirstName")
fun TextView.setFirstName(fullName: String?) {
    fullName?.let {
        val (firstName, _) = it.splitFullName()
        text = firstName
    }
}

@BindingAdapter("setLastName")
fun TextView.setLastName(fullName: String?) {
    fullName?.let {
        val (_, lastName) = it.splitFullName()
        text = lastName
    }
}
