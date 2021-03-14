package com.example.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.core.BR
import com.example.core.R
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VDB : ViewDataBinding, VM : ViewModelType<*, *>>(
    @LayoutRes
    val layoutId: Int
) : AppCompatActivity() {

    protected lateinit var binding: VDB
    protected abstract val viewModel: VM

    protected val compositeDisposable by lazy(::CompositeDisposable)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<VDB>(this, layoutId).apply {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.viewModel, viewModel)
        }

        initObserving()
        overridePendingTransition(R.anim.from_right, R.anim.to_left)
    }

    open fun initObserving() { }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}