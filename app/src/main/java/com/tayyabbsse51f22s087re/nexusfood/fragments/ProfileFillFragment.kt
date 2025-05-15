package com.tayyabbsse51f22s087re.nexusfood.fragments

import BaseFragment
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tayyabbsse51f22s087re.nexusfood.NavigationUtils
import com.tayyabbsse51f22s087re.nexusfood.R
import com.tayyabbsse51f22s087re.nexusfood.databinding.FragmentProfileFillBinding

class ProfileFillFragment : BaseFragment<FragmentProfileFillBinding>(FragmentProfileFillBinding::inflate) {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        // No need for image selection or storage-related code

        binding.btnContinue.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val email = binding.etEmail.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && phoneNumber.isNotEmpty() && email.isNotEmpty()) {
                saveUserProfile(firstName, lastName, phoneNumber, email)
                // Navigate to HomeFragment or save login state
                navController.navigate(R.id.action_profileFillFragment_to_mainFragment,null,
                    NavigationUtils.fadeNavOptions)
                Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
            } else {
                // Show error message if any field is empty
                Log.e("ProfileFillFragment", "All fields are required!")
            }
        }
    }

    private fun saveUserProfile(firstName: String, lastName: String, phoneNumber: String, email: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userProfile = hashMapOf(
                "firstName" to firstName,
                "lastName" to lastName,
                "phoneNumber" to phoneNumber,
                "email" to email
            )

            db.collection("users").document(userId)
                .set(userProfile)
                .addOnSuccessListener {
                    Log.d("ProfileFillFragment", "User profile saved successfully.")
                }
                .addOnFailureListener { e ->
                    Log.e("ProfileFillFragment", "Error saving user profile", e)
                }
        }
    }
}