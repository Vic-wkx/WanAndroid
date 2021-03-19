package com.base.library.project.utils

import android.content.Context
import android.content.Intent
import android.os.LocaleList
import com.base.library.R
import com.base.library.project.toast
import com.base.library.rxRetrofit.common.utils.SPUtils
import java.util.*

const val LANGUAGE = "LANGUAGE"
const val CHINESE = "CHINESE"
const val ENGLISH = "ENGLISH"

object LanguageUtils {
    private val language: String
        // get the newest value
        get() = SPUtils.getInstance().getString(LANGUAGE, CHINESE)
    private val currentLocale: Locale
        get() = if (language == CHINESE) Locale.CHINA else Locale.US

    fun changeLanguage(context: Context, language: String) {
        if (this.language == language) {
            context.toast(R.string.no_need_change_language_hint)
            return
        }
        SPUtils.getInstance().put(LANGUAGE, language)
        restart(context)
    }

    private fun restart(context: Context) {
        context.startActivity(context.packageManager.getLaunchIntentForPackage(context.packageName)?.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }

    fun getContextWithLanguage(newBase: Context?): Context? {
        return newBase?.createConfigurationContext(newBase.resources.configuration.apply {
            setLocales(LocaleList(currentLocale))
        })
    }
}