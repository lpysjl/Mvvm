package com.pear.toutiao.app.listener

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/13 08:41
 * describe :
 */
interface ModelCallBack<T> {

    fun Success(b: T)

    fun Fail(f: T)

}