package com.base.library.project

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.base.library.project.utils.LanguageUtils

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity(), IBase {
    protected lateinit var binding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initViewBinding() as T
        setContentView(binding.root)
        initView()
        initData()
    }


    override fun attachBaseContext(newBase: Context?) {
        // set language
        super.attachBaseContext(LanguageUtils.getContextWithLanguage(newBase))
    }
}