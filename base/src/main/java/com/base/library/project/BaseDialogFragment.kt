package com.base.library.project

import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding


abstract class BaseDialogFragment<T : ViewBinding> : DialogFragment(), IBase {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        @Suppress("UNCHECKED_CAST")
        _binding = initViewBinding(container) as T
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set a background, otherwise the layout percent won't take effect
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setGravity(gravity())
        initView()
        initData()
    }

    override fun onStart() {
        super.onStart()
        setLayoutPercent(widthPercentage(), heightPercentage())
    }

    open fun widthPercentage(): Int {
        return ViewGroup.LayoutParams.MATCH_PARENT
    }

    open fun heightPercentage(): Int {
        return ViewGroup.LayoutParams.MATCH_PARENT
    }

    open fun gravity(): Int {
        return Gravity.CENTER
    }

    private fun setLayoutPercent(widthPercentage: Int, heightPercentage: Int) {
        val rect = Rect(0, 0, resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
        val width = when (widthPercentage) {
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT -> widthPercentage
            else -> (rect.width() * (widthPercentage.toFloat() / 100)).toInt()
        }
        val height = when (heightPercentage) {
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT -> heightPercentage
            else -> (rect.height() * (heightPercentage.toFloat() / 100)).toInt()
        }
        dialog?.window?.setLayout(width, height)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}