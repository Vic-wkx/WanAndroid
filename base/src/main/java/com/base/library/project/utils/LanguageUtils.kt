package com.base.library.project.utils

import android.content.Context
import android.content.Intent
import com.base.library.R
import com.base.library.rxRetrofit.common.utils.SPUtils
import org.jetbrains.anko.toast
import java.util.*

const val LANGUAGE = "LANGUAGE"
const val CHINESE = "CHINESE"
const val ENGLISH = "ENGLISH"

object LanguageUtils {
    private val language: String
        // get the newest value
        get() = SPUtils.getInstance().getString(LANGUAGE, CHINESE)
    val currentLocale: Locale
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
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        })
    }
}