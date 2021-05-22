package com.akshatsahijpal.covidone.ui.fragment.upload

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.akshatsahijpal.covidone.R
import com.akshatsahijpal.covidone.data.CovidData
import com.akshatsahijpal.covidone.databinding.FragmentContributeDataBinding

class ContributeDataFragment : Fragment(R.layout.fragment_contribute_data) {
    private var _binding: FragmentContributeDataBinding? = null
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentContributeDataBinding.bind(view)
        navController = Navigation.findNavController(view)
        val dropDownList = resources.getStringArray(R.array.resource_drop)
        val adap = ArrayAdapter(requireContext(), R.layout.drop_down_exposed_l, dropDownList)
        _binding!!.dropperOR.setAdapter(adap)
        _binding!!.bntForCreation.setOnClickListener {
            var user = CovidData(
                _binding!!.resourceName.editText?.text.toString(),
                _binding!!.NameIdEd.editText?.text.toString(),
                _binding!!.mobIdEd.editText?.text.toString(),
                _binding!!.emailIdEd1.editText?.text.toString(),
                _binding!!.details.editText?.text.toString(),
                _binding!!.StateCity.editText?.text.toString()
            )
          //  model.upload(user)
        }
    }

}