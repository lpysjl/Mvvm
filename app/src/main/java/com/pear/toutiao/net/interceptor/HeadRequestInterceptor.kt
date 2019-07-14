package com.pear.toutiao.net.interceptor

import com.pear.toutiao.global.Constants
import com.pear.toutiao.utils.SPUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/13 09:24
 * describe :
 */
class HeadRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "bearer " + SPUtils.instance.getString(Constants.TOKEN_NAME))
            .build()
        return chain.proceed(request)
    }
}