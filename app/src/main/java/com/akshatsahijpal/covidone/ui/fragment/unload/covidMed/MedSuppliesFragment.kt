package com.akshatsahijpal.covidone.ui.fragment.unload.covidMed

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
import com.akshatsahijpal.covidone.databinding.FragmentMedSuppliesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedSuppliesFragment : Fragment(R.layout.fragment_med_supplies) {
    private var _binding: FragmentMedSuppliesBinding? = null
    private lateinit var navController: NavController
    private val model by viewModels<CovidMedViewModel>()
    private val adap = CovidMedAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMedSuppliesBinding.bind(view)
        navController = Navigation.findNavController(view)

        setHasOptionsMenu(true)
        _binding?.recyclerView2?.adapter = adap.withLoadStateHeaderAndFooter(
            header = CovidLoadStateAdapter {
                adap.retry()
            },
            footer = CovidLoadStateAdapter {
                adap.retry()
            }
        )
        _binding?.RetryButton?.setOnClickListener {
            adap.retry()
        }
        _binding?.recyclerView2?.layoutManager = LinearLayoutManager(requireContext())
        model.getDataSetr().observe(viewLifecycleOwner) {
            Log.d("run", "res->$it")
            adap.submitData(viewLifecycleOwner.lifecycle, it)
        }
        _binding?.floatingActionButton4?.setOnClickListener {
            navController.navigate(R.id.action_medSuppliesFragment_to_contributeDataFragment)
        }
        adap.addLoadStateListener {
            _binding?.apply {
                progressBar.isVisible = it.source.refresh is LoadState.Loading
                errorMessage.isVisible = it.source.refresh is LoadState.Error
                recyclerView2.isVisible = it.source.refresh is LoadState.NotLoading
                RetryButton.isVisible = it.source.refresh is LoadState.Error
                if (it.source.refresh is LoadState.NotLoading && it.append.endOfPaginationReached && adap.itemCount < 1) {
                    noDataFound.isVisible = true
                    recyclerView2.isVisible = false
                }else{
                    noDataFound.isVisible = false
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
                return false
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