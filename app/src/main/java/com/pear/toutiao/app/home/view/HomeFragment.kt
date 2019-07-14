package com.pear.toutiao.app.home.view

import android.os.Bundle
import com.pear.toutiao.R
import com.pear.toutiao.base.BaseFragment
import com.pear.toutiao.databinding.FragmentHomeBinding
import com.pear.toutiao.app.home.viewmodel.HomeViewModel

/**
 *   author : pear
 *   e-mail : 18360183721@163.com
 *     date : 2019/7/13 11:26
 * describe :
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    val homeViewModel = HomeViewModel()

    override fun init(savedInstanceState: Bundle?) {
        dataBinding?.hvm = homeViewModel
        lifecycle.addObserver(homeViewModel)
    }


    override fun initLayout(): Int = R.layout.fragment_home

}