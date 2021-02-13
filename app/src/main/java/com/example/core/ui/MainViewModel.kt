package com.example.core.ui

import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType

interface MainViewModelType : ViewModelType<MainViewModelType.Input, MainViewModelType.Output> {
    interface Input {

    }
    interface Output {

    }
}

class MainViewModel : BaseViewModel(), MainViewModelType, MainViewModelType.Input, MainViewModelType.Output {

    override val input: MainViewModelType.Input
        get() = this
    override val outout: MainViewModelType.Output
        get() = this

    init {

    }
}