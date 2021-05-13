package com.base.library.project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), IBase {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("~~~", "onCreateView")
        return createBinding(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("~~~", "onViewCreated")
        initView()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        Log.d("~~~", "onDestroyView")
        super.onDestroyView()
        releaseBinding()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): View

    abstract fun releaseBinding()
}