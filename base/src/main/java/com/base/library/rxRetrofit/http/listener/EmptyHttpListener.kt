package com.base.library.rxRetrofit.http.listener

class EmptyHttpListener : HttpListener() {
    override fun onNext(result: String) {

    }

    override fun onError(error: Throwable) {
    }
}