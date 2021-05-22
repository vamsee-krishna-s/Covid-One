package com.akshatsahijpal.covidone.ui.fragment.unload.plasma

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.akshatsahijpal.covidone.R
import com.akshatsahijpal.covidone.databinding.FragmentPlasmaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlasmaFragment : Fragment(R.layout.fragment_plasma) {
    private var _binding: FragmentPlasmaBinding? = null
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlasmaBinding.bind(view)
        navController = Navigation.findNavController(view)
    }

}