package com.cristhianbonilla.support.config

import android.view.View
import androidx.fragment.app.Fragment

fun Fragment.setUpLoader(container: View, loader: View, isVisible: Boolean) {
    if (isVisible) {
        container.visibility = View.INVISIBLE
        loader.visibility = View.VISIBLE
    } else {
        container.visibility = View.VISIBLE
        loader.visibility = View.GONE
    }
}
