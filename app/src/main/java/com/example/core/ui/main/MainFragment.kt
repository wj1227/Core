package com.example.core.ui.main

import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>(
    R.layout.fragment_main
) {
    override val viewModel: MainFragmentViewModel by viewModel()

    override fun init() {

    }
}