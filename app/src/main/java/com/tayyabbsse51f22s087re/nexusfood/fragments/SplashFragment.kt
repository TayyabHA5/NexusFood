package com.tayyabbsse51f22s087re.nexusfood.fragments

import BaseFragment
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.tayyabbsse51f22s087re.nexusfood.NavigationUtils
import com.tayyabbsse51f22s087re.nexusfood.R

import com.tayyabbsse51f22s087re.nexusfood.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPref = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val isFirstLaunch = sharedPref.getBoolean("isFirstLaunch",true)
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn",false)

            val navController = findNavController()
            if (isFirstLaunch){
                sharedPref.edit().putBoolean("isFirstLaunch",false).apply()
                navController.navigate(R.id.action_splashFragment_to_welcomeFragment,null,
                    NavigationUtils.fadeNavOptions)
            }else{
                if (isLoggedIn){
                    navController.navigate(R.id.action_splashFragment_to_mainFragment,
                       null, NavigationUtils.fadeNavOptions)
                }else{
                    navController.navigate(R.id.action_splashFragment_to_loginToAccountFragment2,
                        null, NavigationUtils.fadeNavOptions)
                }
            }
        },1500)



    }



}