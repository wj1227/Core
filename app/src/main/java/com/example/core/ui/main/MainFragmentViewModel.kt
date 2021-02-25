package com.example.core.ui.main

import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType

interface MainFragmentViewModelType : ViewModelType<MainFragmentViewModelType.Input, MainFragmentViewModelType.Output> {
    interface Input {

    }

    interface Output {

    }
}

class MainFragmentViewModel : BaseViewModel(), MainFragmentViewModelType, MainFragmentViewModelType.Input, MainFragmentViewModelType.Output {
    override val input: MainFragmentViewModelType.Input
        get() = this
    override val outout: MainFragmentViewModelType.Output
        get() = this

    init {

    }
}