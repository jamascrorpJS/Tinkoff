package com.jamascrorp.tinkoff

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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

fun Fragment.hideBottom(fragment: Fragment) {
    (activity as AppCompatActivity).apply {
        activity?.findViewById<BottomNavigationView>(R.id.bottom)?.visibility = View.GONE
    }
}


fun Fragment.showBottom(fragment: Fragment) {
    (activity as AppCompatActivity).apply {
        activity?.findViewById<BottomNavigationView>(R.id.bottom)?.visibility = View.VISIBLE
    }
}
