package com.example.core.ui.login

import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType

interface LoginViewModelType : ViewModelType<LoginViewModelType.Input, LoginViewModelType.Output> {
    interface Input {

    }
    interface Output {

    }
}

class LoginViewModel : BaseViewModel(), LoginViewModelType, LoginViewModelType.Input, LoginViewModelType.Output {

    override val input: LoginViewModelType.Input
        get() = this
    override val outout: LoginViewModelType.Output
        get() = this

    init {

    }
}