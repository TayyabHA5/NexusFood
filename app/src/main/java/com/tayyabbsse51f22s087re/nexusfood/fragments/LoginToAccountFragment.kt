package com.tayyabbsse51f22s087re.nexusfood.fragments

import BaseFragment
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.tayyabbsse51f22s087re.nexusfood.NavigationUtils
import com.tayyabbsse51f22s087re.nexusfood.R
import com.tayyabbsse51f22s087re.nexusfood.databinding.FragmentLoginToAccountBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.tayyabbsse51f22s087re.nexusfood.LoadingUtils


class LoginToAccountFragment : BaseFragment<FragmentLoginToAccountBinding>(FragmentLoginToAccountBinding::inflate){
    private lateinit var sharedPref : SharedPreferences
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 123  // Request code for Google Sign-In
    private lateinit var auth : FirebaseAuth
    private lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        sharedPref = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSkip.setOnClickListener {
            navController.navigate(R.id.action_loginToAccountFragment_to_mainFragment,null,
                NavigationUtils.fadeNavOptions)
        }

        navController = findNavController() // Initialize NavController here

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        // Handle Google Sign-In Button Click
        binding.btnContinueWithGoogle.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.e("GoogleSignIn", "Sign-in failed", e)
                LoadingUtils.hide()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        LoadingUtils.show(requireActivity())
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)

            .addOnCompleteListener(requireActivity()) { task ->

                if (task.isSuccessful) {
                    Log.d("FirebaseAuth", "User signed in: ${auth.currentUser?.email}")
                    sharedPref.edit().putBoolean("isLoggedIn",true).apply()


                    // Navigate to HomeFragment or save login state
                    navController.navigate(R.id.action_loginToAccountFragment_to_profileFillFragment,null,
                        NavigationUtils.fadeNavOptions)
                    requireView().postDelayed({
                        LoadingUtils.hide()
                    }, 1000)

                } else {
                    Log.e("FirebaseAuth", "Sign-in failed", task.exception)
                }
            }
    }
}