package com.example.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.core.BR
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes
    val layoutId: Int
) : Fragment() {

    protected lateinit var binding: VDB
    protected abstract val viewModel: VM

    protected val compositeDisposable by lazy(::CompositeDisposable)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("onCreate fragment")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("onattach")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("onCreateView")
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        binding.run {
            lifecycleOwner = this@BaseFragment
            setVariable(BR.viewModel, viewModel)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    abstract fun init()

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}