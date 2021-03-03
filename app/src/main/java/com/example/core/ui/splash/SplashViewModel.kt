package com.example.core.ui.splash

import androidx.lifecycle.LiveData
import com.example.core.base.BaseViewModel
import com.example.core.data.splash.source.SplashRepository
import com.example.core.utils.SingleLiveEvent


class SplashViewModel(repository: SplashRepository) : BaseViewModel() {

    private val _state: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val state: LiveData<Boolean>
        get() = _state

    init {
        _state.value = repository.autoLogin
    }

}