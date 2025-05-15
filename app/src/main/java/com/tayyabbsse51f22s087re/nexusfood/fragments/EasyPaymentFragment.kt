package com.tayyabbsse51f22s087re.nexusfood.fragments

import BaseFragment
import android.os.Bundle

import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.tayyabbsse51f22s087re.nexusfood.R
import com.tayyabbsse51f22s087re.nexusfood.databinding.FragmentEasyPaymentBinding


class EasyPaymentFragment : BaseFragment<FragmentEasyPaymentBinding>(FragmentEasyPaymentBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnNext?.setOnClickListener {
            findNavController().navigate(R.id.action_easyPaymentFragment_to_fastDeliveryFragment)
        }
    }


}