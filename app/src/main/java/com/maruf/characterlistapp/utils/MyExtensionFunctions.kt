package com.maruf.characterlistapp.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.Locale

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(value: T) {
            removeObserver(this)
            observer.onChanged(value)
        }
    })
}
fun String.splitFullName(): Pair<String, String> {
    val fullName = this.lowercase(Locale.ROOT).split(" ")
    val firstName = fullName.first()
    val lastName = fullName.last()
    return Pair(
        firstName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
        lastName.capitalize()
    )
}