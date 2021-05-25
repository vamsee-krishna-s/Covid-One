package com.akshatsahijpal.covidone.ui.fragment.upload

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.akshatsahijpal.covidone.R
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.databinding.FragmentContributeDataBinding
import com.akshatsahijpal.covidone.ui.fragment.upload.vm.ContributeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContributeDataFragment : Fragment(R.layout.fragment_contribute_data) {
    private var _binding: FragmentContributeDataBinding? = null
    private lateinit var navController: NavController
    private val model by viewModels<ContributeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContributeDataBinding.bind(view)
        navController = Navigation.findNavController(view)
        val dropDownList = resources.getStringArray(R.array.resource_drop)
        val adap = ArrayAdapter(requireContext(), R.layout.drop_down_exposed_l, dropDownList)
        _binding!!.dropperOR.setAdapter(adap)
        _binding!!.bntForCreation.setOnClickListener {
            val user = CovidData(
                _binding!!.resourceName.editText?.text.toString(),
                _binding!!.NameIdEd.editText?.text.toString(),
                _binding!!.mobIdEd.editText?.text.toString(),
                _binding!!.emailIdEd1.editText?.text.toString(),
                _binding!!.details.editText?.text.toString(),
                _binding!!.StateCity.editText?.text.toString()
            )
            model.upload(user, dropDownList)
            Toast.makeText(requireContext(), "Data Uploaded!!!", Toast.LENGTH_SHORT).show()
            navController.navigate(R.id.action_contributeDataFragment_to_entryFragment)
        }
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}