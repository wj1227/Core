package com.example.core.ui.ownermain

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.base.BaseActivity
import com.example.core.databinding.ActivityOwnerMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OwnerMainActivity : BaseActivity<ActivityOwnerMainBinding, OwnerMainViewModel>(
    R.layout.activity_owner_main
) {
    override val viewModel: OwnerMainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initObserving() {
        super.initObserving()

        with(viewModel) {
            users.observe(this@OwnerMainActivity, {
                println("user ok")
            })
            selfcalls.observe(this@OwnerMainActivity, {
                println("selfcalls ok")
            })
            suggestions.observe(this@OwnerMainActivity, {
                println("suggestions ok")
            })
            orders.observe(this@OwnerMainActivity, {
                println("orders ok")
            })
        }
    }
}