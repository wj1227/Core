package com.example.core.ui.login

import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentLoginBinding
import com.example.core.ui.MainViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class LoginFragment : BaseFragment<FragmentLoginBinding, MainViewModel>(
    R.layout.fragment_login
) {

    override val viewModel: MainViewModel by viewModel()

    override fun init() {
        Completable.timer(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { findNavController().navigate(R.id.action_loginFragment_to_termsOfUseFragment) }
            .addTo(compositeDisposable)
    }

}