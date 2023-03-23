package com.jamascrorp.tinkoff

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.showAction() {
    (activity as AppCompatActivity).supportActionBar?.show()
}

fun Fragment.hideAction() {
    (activity as AppCompatActivity).supportActionBar?.hide()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
