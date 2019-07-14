package com.pear.toutiao.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/13 09:45
 * describe :
 */
abstract class BaseFragment<T : ViewDataBinding?> : Fragment() {
    var dataBinding: T? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate<T>(inflater, initLayout(), container, true)
        init(savedInstanceState)
        set()
        return dataBinding?.getRoot()
    }

    protected open fun set() {

    }

    abstract fun init(savedInstanceState: Bundle?)

    abstract fun initLayout(): Int

}