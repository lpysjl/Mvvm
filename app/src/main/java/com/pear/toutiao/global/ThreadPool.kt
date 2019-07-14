package com.pear.toutiao.global

import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/14 19:52
 * describe :
 */
class ThreadPool {

    companion object {
        private val newThreadPool by lazy {
            Executors.newCachedThreadPool(object : ThreadFactory {
                var count = 0
                override fun newThread(r: Runnable?): Thread {
                    println("新开的线程: newThreadPool_$count")
                    val thread = Thread(r, "newThreadPool_${count++}")
                    thread.setUncaughtExceptionHandler { t, e ->
                        //处理非正常的线程中止,多线程中通过trycatch试图捕获线程的异常是不可取的
                        println(t.name)
                        e.printStackTrace()
                    }
                    return thread
                }
            })
        }

        fun newThread(r: () -> Unit) {
            newThreadPool.execute(r)
        }
    }

}