package com.base.library.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<T : ViewBinding> : Fragment(), IBase {

    private var _binding: T? = null
    protected val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initViewBinding(container)
        return binding.root
    }

    private fun initViewBinding(container: ViewGroup?) {
        val superClass = javaClass.genericSuperclass as ParameterizedType
        val actualClass = superClass.actualTypeArguments.first() as Class<*>
        val inflateMethod = actualClass.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        _binding = inflateMethod.invoke(null, layoutInflater, container, false) as T
    }

    private fun releaseView() {
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        releaseView()
    }
}