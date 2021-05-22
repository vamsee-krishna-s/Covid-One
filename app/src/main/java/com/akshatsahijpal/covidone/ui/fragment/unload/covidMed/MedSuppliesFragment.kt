package com.akshatsahijpal.covidone.ui.fragment.unload.covidMed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.akshatsahijpal.covidone.R
import com.akshatsahijpal.covidone.databinding.FragmentMedSuppliesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedSuppliesFragment : Fragment(R.layout.fragment_med_supplies) {
    private var _binding: FragmentMedSuppliesBinding? = null
    private lateinit var navController: NavController
    private val model by viewModels<CovidMedViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMedSuppliesBinding.bind(view)
        navController = Navigation.findNavController(view)
        model.covidMedData.observe(viewLifecycleOwner){
            Log.d("run", "res->$it")
        }
    }
}