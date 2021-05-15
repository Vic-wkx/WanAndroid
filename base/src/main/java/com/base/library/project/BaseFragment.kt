package com.base.library.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment(), IBase {

    private var _binding: T? = null
    protected val binding get() = _binding!!
    private var loaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = initViewBinding(container) as T
        return binding.root
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden && !loaded) {
            loaded = true
            initView()
            initData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}