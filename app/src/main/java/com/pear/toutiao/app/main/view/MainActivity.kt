package com.pear.toutiao.app.main.view

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.pear.toutiao.R
import com.pear.toutiao.app.home.view.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(
            R.id.fl_show, HomeFragment()
        ).commit()
        rg_main_navigation.setOnCheckedChangeListener { _, checkedId ->
            run {
                when (checkedId) {
                    R.id.rb_main_home -> supportFragmentManager.beginTransaction().replace(
                        R.id.fl_show, HomeFragment()
                    ).commit()
                    R.id.rb_main_duanzi -> supportFragmentManager.beginTransaction().commit()
                    R.id.rb_main_video -> supportFragmentManager.beginTransaction().commit()
                    R.id.rb_main_my -> supportFragmentManager.beginTransaction().commit()
                    else -> Log.d("", "")
                }
            }
        }
    }

}
