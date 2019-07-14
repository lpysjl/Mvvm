package com.pear.toutiao.app

import android.app.Application
import android.content.pm.PackageManager
import com.pear.toutiao.global.Constants
import com.pear.toutiao.global.ThreadPool.Companion.newThread
import com.pear.toutiao.net.RetorfitHelper
import com.pear.toutiao.net.UrlConstants
import com.pear.toutiao.utils.L
import com.pear.toutiao.utils.SPUtils

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/14 19:06
 * describe :
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        L.init("TouTiao", true)
        SPUtils.instance.init(this, "Totiao")
        getToken()
    }

    fun getToken() {
        val appinfo = packageManager.getApplicationInfo(this.packageName, PackageManager.GET_META_DATA)
        val autocode = appinfo.metaData.getString(Constants.AUTHCODE)
        val map = HashMap<String, String>()
        map["grant_type"] = "password"
        map["username"] = autocode
        map["password"] = ""
        newThread {
            val execute =
                RetorfitHelper.instance.getServer().getDataWithPost(UrlConstants.TOKEN, map)
                    .execute()
            if (execute.isSuccessful) {
                val jsonString = execute.body()?.string()

                L.d("json ->$jsonString \n")

            } else {
                L.d("获取Token失败")
            }
        }

    }
}