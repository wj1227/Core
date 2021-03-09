package com.example.core.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.base.BaseActivity
import com.example.core.databinding.ActivityMainBinding
import com.example.core.ui.login.LoginActivity
import com.example.core.ui.order.OrderActivity
import com.example.core.ui.orderlist.OrderListActivity
import com.example.core.ui.profilechange.ProfileChangeActivity
import com.example.core.ui.selfcall.SelfCallActivity
import com.example.core.ui.suggestion.SuggestionActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main
) {
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initObserving() {
        super.initObserving()

        with(viewModel) {
            mainState.observe(this@MainActivity, Observer { state ->
                when (state) {
                    MainViewModel.MainState.LOGOUT_SUCCESS -> goLogin()
                    MainViewModel.MainState.PROFILE_CHANGE -> goProfileChange()
                    MainViewModel.MainState.SELF_CALL -> goSelfCall()
                    MainViewModel.MainState.SUGGESTION -> goSuggestion()
                    MainViewModel.MainState.ORDER -> goOrder()
                    MainViewModel.MainState.ORDER_LIST -> goOrderList()
                }
            })
        }
    }

    private fun goLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun goProfileChange() {
        startActivity(Intent(this, ProfileChangeActivity::class.java))
    }
    private fun goSelfCall() {
        startActivity(Intent(this, SelfCallActivity::class.java))
    }
    private fun goSuggestion() {
        startActivity(Intent(this, SuggestionActivity::class.java))
    }
    private fun goOrder() {
        startActivity(Intent(this, OrderActivity::class.java))
    }
    private fun goOrderList() {
        startActivity(Intent(this, OrderListActivity::class.java))
    }
}