package com.example.core.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.core.ui.login.LoginActivity
import com.example.core.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

//todo 뷰모델 뷰 정리 해야함(매니페스트 포함)
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this, Observer { result ->
            if (result) {
                //goMain()
                goLogin()
            } else {
                goLogin()
            }
        })
    }

    private fun goLogin() {
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
        }, 2000)
    }

    private fun goMain() {
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 2000)
    }
}