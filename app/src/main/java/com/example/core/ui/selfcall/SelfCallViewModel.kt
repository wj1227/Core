package com.example.core.ui.selfcall

import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType

interface SelfCallViewModelType : ViewModelType<SelfCallViewModelType.Input, SelfCallViewModelType.Output> {
    interface Input {

    }

    interface Output {

    }
}

class SelfCallViewModel : BaseViewModel(), SelfCallViewModelType, SelfCallViewModelType.Input, SelfCallViewModelType.Output {
    override val input: SelfCallViewModelType.Input
        get() = this
    override val outout: SelfCallViewModelType.Output
        get() = this

    init {

    }
}