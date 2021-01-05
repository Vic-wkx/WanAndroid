package com.base.library.project

import android.content.Context
import android.os.Bundle
import android.os.LocaleList
import androidx.appcompat.app.AppCompatActivity
import com.base.library.project.utils.LanguageUtils

abstract class BaseActivity : AppCompatActivity(), IBase {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView()
        initData()
    }

    override fun attachBaseContext(newBase: Context?) {
        // set language
        super.attachBaseContext(LanguageUtils.getContextWithLanguage(newBase))
    }
}