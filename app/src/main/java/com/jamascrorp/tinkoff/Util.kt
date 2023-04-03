package com.jamascrorp.tinkoff

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jamascrorp.tinkoff.data.network.Network
import kotlinx.coroutines.delay
import okhttp3.ResponseBody

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

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)

            removeObserver(this)
        }
    })
}

fun <T> LiveData<T>.observeNetworkOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            if (t is Network.Success<*>) {
                removeObserver(this)
            }
        }
    })
}
