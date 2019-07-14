package com.pear.toutiao.app.home.viewmodel

import android.arch.lifecycle.*
import com.pear.toutiao.app.home.model.NewsType

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/13 11:26
 * describe :
 */
class HomeViewModel : ViewModel(), LifecycleObserver {
    val newTypesMld = MutableLiveData<List<NewsType>>()
    val newsTypes = ArrayList<NewsType>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun viewCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun viewResume() {

    }


}