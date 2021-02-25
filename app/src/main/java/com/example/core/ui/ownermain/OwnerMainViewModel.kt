package com.example.core.ui.ownermain

import com.example.core.base.BaseViewModel
import com.example.core.base.ViewModelType

interface OwnerMainViewModelType : ViewModelType<OwnerMainViewModelType.Input, OwnerMainViewModelType.Output> {
    interface Input {

    }

    interface Output {

    }
}

class OwnerMainViewModel : BaseViewModel(), OwnerMainViewModelType, OwnerMainViewModelType.Input, OwnerMainViewModelType.Output {
    override val input: OwnerMainViewModelType.Input
        get() = this
    override val outout: OwnerMainViewModelType.Output
        get() = this

    init {

    }
}