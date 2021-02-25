package com.example.core.ui.tos

import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentTermsofuseBinding
import com.example.core.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TermsOfUseFragment : BaseFragment<FragmentTermsofuseBinding, MainViewModel>(
    R.layout.fragment_termsofuse
) {
    override val viewModel: MainViewModel by viewModel()

    override fun init() {
//        Completable.timer(2, TimeUnit.SECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { findNavController().navigate(R.id) }
//            .addTo(compositeDisposable)
    }

}