package com.example.core.ui.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.base.BaseActivity
import com.example.core.databinding.ActivityTutorialBinding
import com.example.core.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TutorialActivity : BaseActivity<ActivityTutorialBinding, TutorialViewModel>(
    R.layout.activity_tutorial
) {
    override val viewModel: TutorialViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewModel) {
            start.observe(this@TutorialActivity, Observer {
                goLogin()
            })
        }
    }

    override fun initObserving() {
        super.initObserving()
    }

    private fun goLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}