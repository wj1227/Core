package com.example.core.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.core.ui.login.LoginActivity
import com.example.core.ui.main.MainActivity
import com.example.core.ui.ownermain.OwnerMainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

//todo 여기 다시 처리해야함...백 하면 다시 재생성안됨 (뷰모델때문인듯)
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.state.observe(this, Observer { result ->
            if (result) {
                //goMain()
                goOwner()
            } else {
                goLogin()
            }
        })
    }
    private fun goLogin() = Handler(Looper.getMainLooper()).postDelayed({
        startActivity(Intent(this, LoginActivity::class.java))
    }, 2L)

    private fun goMain() = Handler(Looper.getMainLooper()).postDelayed({
        startActivity(Intent(this, MainActivity::class.java))
    }, 2L)

    private fun goOwner() = Handler(Looper.getMainLooper()).postDelayed({
        startActivity(Intent(this, OwnerMainActivity::class.java))
    }, 2L)

}