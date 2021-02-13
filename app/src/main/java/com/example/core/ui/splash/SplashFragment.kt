package com.example.core.ui.splash

import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentSplashBinding
import com.example.core.ui.MainViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SplashFragment : BaseFragment<FragmentSplashBinding, MainViewModel>(
    R.layout.fragment_splash
) {

    override val viewModel: MainViewModel by viewModel()

    override fun init() {
        Completable.timer(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { findNavController().navigate(R.id.action_splashFragment_to_tutorialFragment) }
            .addTo(compositeDisposable)
    }

}