package com.wkxjc.wanandroid.me.language

import com.base.library.project.BaseActivity
import com.wkxjc.wanandroid.R
import com.base.library.project.utils.CHINESE
import com.base.library.project.utils.ENGLISH
import com.base.library.project.utils.LanguageUtils
import kotlinx.android.synthetic.main.activity_language.*


class LanguageActivity : BaseActivity() {

    override fun layoutId() = R.layout.activity_language

    override fun initView() {
        btnChinese.setOnClickListener {
            LanguageUtils.changeLanguage(this, CHINESE)
        }
        btnEnglish.setOnClickListener {
            LanguageUtils.changeLanguage(this, ENGLISH)
        }
    }

    override fun initData() {
    }
}