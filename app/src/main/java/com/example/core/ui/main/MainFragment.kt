package com.example.core.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.function.LongUnaryOperator

class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>(
    R.layout.fragment_main
) {
    override val viewModel: MainFragmentViewModel by viewModel()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        println("onon")
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }
    override fun init() {
        with(viewModel) {
            mainState.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    MainFragmentViewModel.MainState.LOGOUT_SUCCESS -> goLogin()
                    MainFragmentViewModel.MainState.PROFILE_CHANGE -> goProfileChange()
                    MainFragmentViewModel.MainState.SELF_CALL -> goSelfCall()
                    MainFragmentViewModel.MainState.SUGGESTION -> goSuggestion()
                    MainFragmentViewModel.MainState.ORDER -> goOrder()
                    MainFragmentViewModel.MainState.ORDER_LIST -> goOrderList()
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.bindRx()
    }

    private fun goLogin() = findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
    private fun goProfileChange() = findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
    private fun goSelfCall() = findNavController().navigate(R.id.action_mainFragment_to_selfCallFragment)
    private fun goSuggestion() = findNavController().navigate(R.id.action_mainFragment_to_suggestionFragment)
    private fun goOrder() = findNavController().navigate(R.id.action_mainFragment_to_orderFragment)
    private fun goOrderList() = findNavController().navigate(R.id.action_mainFragment_to_orderListFragment)
}