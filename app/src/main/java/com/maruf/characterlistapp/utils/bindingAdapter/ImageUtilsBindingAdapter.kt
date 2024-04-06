package com.maruf.characterlistapp.utils.bindingAdapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.maruf.characterlistapp.R
import java.util.Locale

@BindingAdapter("loadImageFromUrl")
fun ShapeableImageView.loadImageFromUrl(imageUrl: String) {
    load(imageUrl) {
        crossfade(600)
        placeholder(R.drawable.ic_error_placeholder)
        error(R.drawable.ic_error_placeholder)
    }
}

fun String.splitFullName(): Pair<String, String> {
    val fullName = this.lowercase(Locale.ROOT).split(" ")
    val firstName = fullName.first()
    val lastName = fullName.last()
    return Pair(firstName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }, lastName.capitalize())
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
