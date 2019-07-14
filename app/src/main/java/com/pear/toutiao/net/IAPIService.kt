package com.pear.toutiao.net

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/14 19:26
 * describe :
 */
interface IAPIService {
    @GET
    fun getDataWithGet(@Url url: String, @QueryMap map: Map<String, String>): Call<ResponseBody>

    @POST
    @FormUrlEncoded
    fun getDataWithPost(@Url url: String, @FieldMap map: Map<String, String>): Call<ResponseBody>
}