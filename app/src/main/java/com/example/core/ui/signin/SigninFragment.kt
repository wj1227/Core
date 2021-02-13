package com.example.core.ui.signin

import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentSigninBinding
import com.example.core.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SigninFragment : BaseFragment<FragmentSigninBinding, MainViewModel>(
    R.layout.fragment_signin
) {
    override val viewModel: MainViewModel by viewModel()

    override fun init() {

    }

}