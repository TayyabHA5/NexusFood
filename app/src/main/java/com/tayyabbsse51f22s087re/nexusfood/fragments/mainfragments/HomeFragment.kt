package com.tayyabbsse51f22s087re.nexusfood.fragments.mainfragments

import BaseFragment
import HomeViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tayyabbsse51f22s087re.nexusfood.NavigationUtils
import com.tayyabbsse51f22s087re.nexusfood.R
import com.tayyabbsse51f22s087re.nexusfood.databinding.FragmentHomeBinding
import kotlinx.coroutines.Runnable

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private var isFirstLoad = true
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onStart() {
        super.onStart()
        showLoader()
        view?.postDelayed(Runnable {
            hideLoader()
        }, 2000)
    }

    override fun onResume() {
        super.onResume()
        // No specific logic for first load needed here anymore
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.userName.observe(viewLifecycleOwner, Observer { name ->
            binding.tvUserName.text = name ?: "Guest"
        })

        binding.ivCartIcon.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment2_to_cartFragment3, null,
                NavigationUtils.fadeNavOptions
            )
        }
        binding.ivNotifications.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment2_to_notificationFragment, null,
                NavigationUtils.fadeNavOptions
            )
        }

        // Optional: Implement pull-to-refresh if needed
        // binding.swipeRefresh.setOnRefreshListener {
        //     // Trigger a refresh of data if necessary
        //     binding.swipeRefresh.isRefreshing = false
        // }
    }
}