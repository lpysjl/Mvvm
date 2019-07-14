package com.pear.toutiao.utils

import android.util.Log
import java.lang.NullPointerException
import java.lang.RuntimeException

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/14 19:57
 * describe :   LogUtils 用着方便
 */
class L {
    companion object {
        private var tag = ""
        var logSwitch = false
        fun init(_tag: String, _logSwitch: Boolean) {
            if (_tag == null) {
                throw NullPointerException("tag")
            }
            if (_logSwitch == null) {
                throw NullPointerException("tag")
            }
            tag = _tag
            logSwitch = _logSwitch
        }

        fun d(tag: String, msg: String) {
            if (!logSwitch)
                return
            Log.d(tag, msg)
        }

        fun d(msg: String) {
            if (!logSwitch)
                return
            if (tag.isEmpty())
                throw RuntimeException("LogUtils的tag为空")
            Log.d(tag, msg)
        }

        fun e(tag: String, msg: String) {
            if (!logSwitch)
                return
            Log.e(tag, msg)
        }

        fun e(msg: String) {
            if (!logSwitch)
                return
            if (tag.isEmpty())
                throw RuntimeException("LogUtils的tag为空")
            Log.d(tag, msg)
        }
    }
}