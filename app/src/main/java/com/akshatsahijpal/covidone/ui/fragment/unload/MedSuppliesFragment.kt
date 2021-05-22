package com.akshatsahijpal.covidone.ui.fragment.unload

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.akshatsahijpal.covidone.R
import com.akshatsahijpal.covidone.databinding.FragmentMedSuppliesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedSuppliesFragment : Fragment(R.layout.fragment_med_supplies) {
    private var _binding: FragmentMedSuppliesBinding? = null
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMedSuppliesBinding.bind(view)
        navController = Navigation.findNavController(view)
    }

}