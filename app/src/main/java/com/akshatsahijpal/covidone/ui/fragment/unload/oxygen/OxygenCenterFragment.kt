package com.akshatsahijpal.covidone.ui.fragment.unload.oxygen

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
 import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshatsahijpal.covidone.R
import com.akshatsahijpal.covidone.adapter.covidMedAdapter.CovidLoadStateAdapter
import com.akshatsahijpal.covidone.adapter.covidMedAdapter.CovidMedAdapter
import com.akshatsahijpal.covidone.databinding.FragmentOxygenCenterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OxygenCenterFragment : Fragment(R.layout.fragment_oxygen_center) {
    private var _binding: FragmentOxygenCenterBinding? = null
    private lateinit var navController: NavController
    private val model by viewModels<OxygenViewModel>()
    private val adap = CovidMedAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOxygenCenterBinding.bind(view)
        navController = Navigation.findNavController(view)
      //  var typeOfDataToReturn: String = resources.getStringArray(R.array.resource_drop)[0]
        setHasOptionsMenu(true)
        _binding?.recyclerView101?.adapter = adap.withLoadStateHeaderAndFooter(
            header = CovidLoadStateAdapter {
                adap.retry()
            },
            footer = CovidLoadStateAdapter {
                adap.retry()
            }
        )
        _binding?.recyclerView101?.layoutManager = LinearLayoutManager(requireContext())
        model.getDataSetr().observe(viewLifecycleOwner) {
            Log.d("run", "res->$it")
            adap.submitData(viewLifecycleOwner.lifecycle, it)
        }
        _binding?.floatingActionButton3?.setOnClickListener {
            navController.navigate(R.id.action_medSuppliesFragment_to_contributeDataFragment)
        }
        adap.addLoadStateListener {
            _binding?.apply {
                progressBar211.isVisible = it.source.refresh is LoadState.Loading
                errorMessage211.isVisible = it.source.refresh is LoadState.Error
                recyclerView101.isVisible = it.source.refresh is LoadState.NotLoading
                RetryButton211.isVisible = it.source.refresh is LoadState.Error
                if (it.source.refresh is LoadState.NotLoading && it.append.endOfPaginationReached && adap.itemCount < 1) {
                    noDataFound211.isVisible = true
                    recyclerView101.isVisible = false
                }else{
                    noDataFound211.isVisible = false
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.call_menu, menu)
        val searchItem = menu.findItem(R.id.actionSearch)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    _binding?.recyclerView101?.scrollToPosition(0)
                    model.searchModel(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}