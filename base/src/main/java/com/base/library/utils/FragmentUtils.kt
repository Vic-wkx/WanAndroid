package com.base.library.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentUtils {

    fun add(fragmentManager: FragmentManager, containerId: Int, vararg fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        fragment.forEach {
            transaction.add(containerId, it, "")
        }
        transaction.commitNow()
    }

    fun show(fragmentManager: FragmentManager, fragment: Fragment) {
        fragmentManager.beginTransaction().show(fragment).commit()
        fragmentManager.fragments.filter { it != fragment }.forEach {
            fragmentManager.beginTransaction().hide(it).commit()
        }
    }
}