package com.tayyabbsse51f22s087re.nexusfood.fragments

import BaseFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tayyabbsse51f22s087re.nexusfood.R
import com.tayyabbsse51f22s087re.nexusfood.databinding.FragmentWelcomeBinding


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tvNext?.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_orderForFoodFragment)
        }
    }


}