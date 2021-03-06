package com.example.core.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.core.R
import com.example.core.base.BaseActivity
import com.example.core.base.BaseDialogFragment
import com.example.core.constants.LOADING
import com.example.core.databinding.ActivityLoginBinding
import com.example.core.ui.signin.SigninActivity
import com.example.core.utils.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

//todo 로그인 액티비티 정리 해야함
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(
    R.layout.activity_login
) {
    override val viewModel: LoginViewModel by viewModel()
    private val loadingView by lazy { BaseDialogFragment(R.layout.fragment_loading) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTextWatcher()
    }

    override fun initObserving() {
        super.initObserving()

        with(viewModel) {
            loginState.observe(this@LoginActivity, Observer { state ->
                when (state) {
                    LoginViewModel.LoginState.EMAIL_EMPTY -> toast("이메일을 입력해주세요")
                    LoginViewModel.LoginState.PASSWORD_EMPTY -> toast("비밀번호를 입력해주세요")
                    LoginViewModel.LoginState.GO_SIGNIN -> goSignin()
                    LoginViewModel.LoginState.GO_MAIN -> ""
                    LoginViewModel.LoginState.GO_OWNER -> ""
                }
            })
            loading.observe(this@LoginActivity, Observer { result ->
                if (result) {
                    loadingView.show(supportFragmentManager, LOADING)
                } else {
                    loadingView.dismissAllowingStateLoss()
                }
            })
            errorMessage.observe(this@LoginActivity, Observer {
                toast(it)
            })
        }
    }

    private fun initTextWatcher() {
        with(binding) {
            etEmail.addTextChangedListener { viewModel?.emailOnNext(it.toString()) }
            etPassword.addTextChangedListener { viewModel?.passwordOnNext(it.toString()) }
        }
    }

    private fun toast(message: String) = this.showToast(message)
    private fun goSignin() = startActivity(Intent(this, SigninActivity::class.java))
    //private fun goSignin() = findNavController().navigate(R.id.action_loginFragment_to_signinFragment)
    //private fun goMain() = findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
    //private fun goOwnerMain() = findNavController().navigate(R.id.action_loginFragment_to_ownerMainFragment)
}