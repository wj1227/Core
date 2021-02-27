package com.example.core.ui.selfcall

import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentSelfcallBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelfCallFragment : BaseFragment<FragmentSelfcallBinding, SelfCallViewModel>(
    R.layout.fragment_selfcall
) {
    override val viewModel: SelfCallViewModel by viewModel()

    override fun init() {

    }
}