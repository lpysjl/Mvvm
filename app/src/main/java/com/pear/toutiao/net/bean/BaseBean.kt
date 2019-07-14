package com.pear.toutiao.net.bean

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/13 11:44
 * describe :
 */
class BaseBean<T> {
    var code: Int? = null
    var data: T? = null
    var msg: String? = null

}