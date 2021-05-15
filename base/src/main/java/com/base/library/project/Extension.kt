package com.base.library.project

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

inline fun <reified T : Context> Context.myStartActivity(vararg pairs: Pair<String, Any?>) =
    startActivity(Intent(this, T::class.java).apply {
        pairs.forEach {
            when (it.second) {
                is Int -> putExtra(it.first, it.second as Int)
                is String -> putExtra(it.first, it.second as String)
            }
        }
    })

inline fun <reified T : Context> Fragment.myStartActivity(vararg pairs: Pair<String, Any?>) = context?.myStartActivity<T>(*pairs)

fun Context.showToast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(@StringRes content: Int) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}

fun Activity.initViewBinding(): ViewBinding {
    val inflateMethod = getActualBindingClass(javaClass).getDeclaredMethod("inflate", LayoutInflater::class.java)
    return inflateMethod.invoke(null, layoutInflater) as ViewBinding
}

fun Fragment.initViewBinding(container: ViewGroup?): ViewBinding {
    val inflateMethod = getActualBindingClass(javaClass).getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
    return inflateMethod.invoke(null, layoutInflater, container, false) as ViewBinding
}

fun getActualBindingClass(javaClass: Class<*>): Class<*> {
    return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<*>
}