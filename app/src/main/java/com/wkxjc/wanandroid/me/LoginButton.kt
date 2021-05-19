package com.wkxjc.wanandroid.me

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

class LoginButtonLayout(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    val button = Button(context, attributeSet)
    val progressBar = ProgressBar(context)
    val text = button.getText().toString()
    var status = Status.NORMAL
        set(value) {
            field = value
            when (field) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    button.setText("")
                }
                Status.NORMAL -> {
                    progressBar.visibility = View.GONE
                    button.setText(text)
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