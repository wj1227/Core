package com.example.core.ui.ownermain

import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentOwnerMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OwnerMainFragment : BaseFragment<FragmentOwnerMainBinding, OwnerMainViewModel>(
    R.layout.fragment_owner_main
) {
    override val viewModel: OwnerMainViewModel by viewModel()

    override fun init() {

    }
}