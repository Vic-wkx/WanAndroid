package com.base.library.rxRetrofit.http.api

import android.util.Log
import java.net.CookieHandler
import java.net.URI

class MyCookieHandler :CookieHandler(){
    override fun put(uri: URI?, responseHeaders: MutableMap<String, MutableList<String>>?) {
        Log.d("~~~","")
    }

    override fun get(uri: URI?, requestHeaders: MutableMap<String, MutableList<String>>?): MutableMap<String, MutableList<String>> {
        return requestHeaders!!
    }

}