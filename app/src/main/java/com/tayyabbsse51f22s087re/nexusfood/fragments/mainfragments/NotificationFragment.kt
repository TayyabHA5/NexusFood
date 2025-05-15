package com.example.foodnexus.fragments.mainfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.tayyabbsse51f22s087re.nexusfood.MainActivity
import com.tayyabbsse51f22s087re.nexusfood.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.main_bottom_nav)?.visibility = View.GONE

    }
}