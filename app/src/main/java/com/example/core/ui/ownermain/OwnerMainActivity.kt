package com.example.core.ui.ownermain

import android.os.Bundle
import com.example.core.R
import com.example.core.base.BaseActivity
import com.example.core.databinding.ActivityOwnerMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OwnerMainActivity : BaseActivity<ActivityOwnerMainBinding, OwnerMainViewModel>(
    R.layout.activity_owner_main
) {
    override val viewModel: OwnerMainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initObserving() {
        super.initObserving()
    }
}