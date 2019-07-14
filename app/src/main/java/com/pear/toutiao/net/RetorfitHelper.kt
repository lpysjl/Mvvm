package com.pear.toutiao.net

import com.pear.toutiao.net.interceptor.HeadRequestInterceptor
import com.pear.toutiao.net.interceptor.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/12 16:56
 * describe :
 */
class RetorfitHelper private constructor() {
    private val baseURL = "http://api.city2sky.cn:8088"
    private val DEFAULT_TIMEOUT: Long = 30

    companion object {
        val instance: RetorfitHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetorfitHelper()
        }
    }

    private val client = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .addInterceptor(HeadRequestInterceptor())
        .addInterceptor(LogInterceptor())
        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(client)
        .build()

    fun  getServer(): IAPIService {
        return retrofit.create(IAPIService::class.java)
    }

}