package com.wkxjc.wanandroid.me.language

import com.base.library.project.BaseActivity
import com.base.library.project.utils.CHINESE
import com.base.library.project.utils.ENGLISH
import com.base.library.project.utils.LanguageUtils
import com.wkxjc.wanandroid.databinding.ActivityLanguageBinding


class LanguageActivity : BaseActivity() {
    private val binding by lazy { ActivityLanguageBinding.inflate(layoutInflater) }
    override fun createBinding() = binding.root

    override fun initView() {
        binding.btnChinese.setOnClickListener {
            LanguageUtils.changeLanguage(this, CHINESE)
        }
        binding.btnEnglish.setOnClickListener {
            LanguageUtils.changeLanguage(this, ENGLISH)
        }
    }

    override fun initData() {
    }
}