package com.akshatsahijpal.covidone.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.akshatsahijpal.covidone.R
import com.akshatsahijpal.covidone.databinding.FragmentContributeDataBinding

class ContributeDataFragment : Fragment(R.layout.fragment_contribute_data) {
    private var _binding: FragmentContributeDataBinding? = null
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContributeDataBinding.bind(view)
        navController = Navigation.findNavController(view)
    }

}