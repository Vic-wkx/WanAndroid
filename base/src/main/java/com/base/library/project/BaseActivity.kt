package com.base.library.project

import android.content.Context
import android.os.Bundle
import android.view.View
import android.os.LocaleList
import androidx.appcompat.app.AppCompatActivity
import com.base.library.project.utils.LanguageUtils

abstract class BaseActivity : AppCompatActivity(), IBase {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createBinding())
        initView()
        initData()
    }

    protected abstract fun createBinding(): View

    override fun attachBaseContext(newBase: Context?) {
        // set language
        super.attachBaseContext(LanguageUtils.getContextWithLanguage(newBase))
    }
}