package com.pear.toutiao.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/13 09:39
 * describe :
 */
abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity() {
    var databinding: T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView<T>(this, initContentView())
        init(savedInstanceState)
        set()
    }

    protected open fun set() {

    }

    abstract fun init(savedInstanceState: Bundle?)

    protected fun refreshData(what: Int) {

    }

    protected abstract fun initContentView(): Int
}