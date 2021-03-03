package com.example.core.ui.tutorial

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.core.R
import com.example.core.base.BaseFragment
import com.example.core.databinding.FragmentTutorialBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

//todo single activity launch mode need.....this fragment button is not working! (maybe new task..task..task?)
class TutorialFragment : BaseFragment<FragmentTutorialBinding, TutorialViewModel>(
    R.layout.fragment_tutorial
) {

    override val viewModel: TutorialViewModel by viewModel()

    override fun init() {
        with(viewModel) {
            start.observe(viewLifecycleOwner, Observer {
                findNavController().navigate(R.id.action_tutorialFragment_to_loginFragment)
            })
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.bindRx()
    }

}