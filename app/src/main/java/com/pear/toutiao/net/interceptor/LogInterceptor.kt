package com.pear.toutiao.net.interceptor

import android.text.TextUtils
import com.pear.toutiao.utils.L
import okhttp3.*

import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.text.SimpleDateFormat
import java.util.Date

import okhttp3.internal.Util.UTF_8

/**
 * Desc:日志拦截器
 * Created by fww on 2019/3/29
 */
class LogInterceptor : Interceptor {
    private val format: SimpleDateFormat

    init {
        format = SimpleDateFormat("HH:mm:ss")
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (L.logSwitch) {
            logRequest(request)
        }

        val response = chain.proceed(request)

        if (L.logSwitch) {
            try {
                logResponse(response)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return response
    }


    private fun logRequest(request: Request) {
        val requestLog = StringBuilder("Request \n")

        //Method
        requestLog.append(String.format("\t Method: %s\n", request.method()))

        //Time
        requestLog.append(String.format("\t Time: %s \n", format.format(Date(System.currentTimeMillis()))))

        //Content-Type
        if (request.body() != null && request.body()!!.contentType() != null)
            requestLog.append(String.format("\t Content-Type: %s \n", request.body()!!.contentType()!!.toString()))

        val url = request.url().toString()
        var params: String? = null

        if (request.method().equals("Get", ignoreCase = true)) {
            val index = url.indexOf("?")
            if (index > 0) {
                params = url.substring(index + 1)
            }
        }

        //URL
        requestLog.append(String.format("\t Request: %s \n", getUrl(request)))

        //params
        if (!TextUtils.isEmpty(params)) {
            requestLog.append("\t Params:\n")
            val arrParam = params!!.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (item in arrParam) {
                val arrParam2 = item.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (arrParam2.size == 2) {
                    try {
                        requestLog.append(
                            String.format(
                                "\t\t %s: %s",
                                arrParam2[0],
                                URLDecoder.decode(arrParam2[1], "UTF-8")
                            )
                        )
                    } catch (e: UnsupportedEncodingException) {
                        e.message?.let { L.Companion.e(it) }
                    }

                }
            }
        }

        //Header
        if (request.headers().size() > 0) {
            requestLog.append("\n\t Headers: \n")
            for (header in request.headers().names()) {
                requestLog.append(String.format("\t\t %s: %s \n", header, request.header(header)))
            }
        }

        //Body
        if (request.body() != null && request.body() is FormBody) {
            requestLog.append("\t Params: \n")

            val body = request.body() as FormBody?
            for (i in 0 until body!!.size()) {
                requestLog.append(String.format("\t\t %s: %s", body.name(i), body.value(i)))
            }
        }

        L.d(requestLog.toString())


    }


    @Throws(IOException::class)
    private fun logResponse(response: Response) {

        val requestLog = StringBuilder("Response \n")

        //URL
        requestLog.append(String.format("\t URL: %s\n", getUrl(response.request())))

        //Time
        requestLog.append(String.format("\t Time: %s \n", format.format(Date(System.currentTimeMillis()))))

        //Header
        if (response.headers().size() > 0) {
            requestLog.append("\t Headers: \n")
            for (header in response.headers().names()) {
                requestLog.append(String.format("\t\t %s: %s \n", header, response.header(header)))
            }
        }

        //Body
        val body = response.body()
        if (body != null) {
            body.source().request(java.lang.Long.MAX_VALUE)

            val strResponse = body.source().buffer().clone()
                .readString(if (body.contentType() != null) body.contentType()!!.charset(UTF_8) else UTF_8)


            val builder = StringBuilder()

            builder.append("\t Body: \n\t")
            var i = 0
            var indentCount = 1
            while (i < strResponse.length) {
                val item = strResponse[i]
                builder.append(item)
                if (item == '{' || item == '[') {
                    indentCount++
                } else if (item == '}' || item == ']') {
                    indentCount--
                } else if (item == ',') {

                } else {
                    i++
                    continue
                }

                builder.append("\n")
                for (j in 0 until indentCount)
                    builder.append("\t")
                i++
            }

            requestLog.append(builder.toString())
        }

        L.d(requestLog.toString())

    }


    private fun getUrl(request: Request): String {
        var url = request.url().toString()

        if (request.method().equals("Get", ignoreCase = true)) {
            val index = url.indexOf("?")
            if (index > 0) {
                url = url.substring(0, index)
            }
        }

        return url
    }
}