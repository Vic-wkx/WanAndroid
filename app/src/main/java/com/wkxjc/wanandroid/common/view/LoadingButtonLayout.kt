package com.wkxjc.wanandroid.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.base.library.project.dp

enum class Status {
    LOADING,
    NORMAL
}

class LoadingButtonLayout(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private val button = Button(context, attributeSet)
    private val progressBar = ProgressBar(context)
    val text = button.text.toString()
    var status = Status.NORMAL
        set(value) {
            field = value
            when (field) {
                Status.LOADING -> {
                    button.isEnabled = false
                    button.text = ""
                    progressBar.visibility = View.VISIBLE
                }
                Status.NORMAL -> {
                    progressBar.visibility = View.GONE
                    button.text = text
                    button.isEnabled = true
                }
            }
        }

    override fun setOnClickListener(listener: OnClickListener?) {
        button.setOnClickListener(listener)
    }

    override fun setEnabled(enabled: Boolean) {
        button.isEnabled = enabled
    }

    init {
        addView(button)
        addView(progressBar)
        progressBar.layoutParams = progressBar.layoutParams.apply {
            width = 25.dp
            height = 25.dp
            (this as LayoutParams).gravity = Gravity.CENTER
        }
        status = Status.NORMAL
    }
}