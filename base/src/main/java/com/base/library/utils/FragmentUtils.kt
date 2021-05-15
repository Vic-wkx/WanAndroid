package com.base.library.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtils {

    fun add(fragmentManager: FragmentManager, containerId: Int, vararg fragment: Fragment) {
        fragmentManager.beginTransaction().apply {
            fragment.forEach {
                add(containerId, it, "")
            }
        }.commitNow()
    }

    fun show(fragmentManager: FragmentManager, fragment: Fragment) {
        fragmentManager.beginTransaction().apply {
            fragmentManager.fragments.forEach {
                hide(it)
            }
            show(fragment)
        }.commit()
    }
}