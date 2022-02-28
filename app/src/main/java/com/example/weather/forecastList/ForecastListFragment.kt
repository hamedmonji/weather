package com.example.weather.forecastList

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.forecast.ForecastData
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastListFragment : Fragment(R.layout.fragment_forecast_list) {
    private val viewModel: ForecastListViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val loading = view.findViewById<ProgressBar>(R.id.loading)
        val forecastRecycler = view.findViewById<RecyclerView>(R.id.forecastRecycler)
        forecastRecycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        forecastRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.forecastState.collect { state ->
                    forecastRecycler.adapter = makeForecastAdapter(state.forecasts)
                    loading.isVisible = state.isFetchingForecasts
                    state.messages.poll()?.let {
                        Snackbar.make(view,it,Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun makeForecastAdapter(forecasts: List<ForecastData>) =
        ForecastRecyclerAdapter(forecasts) { forecastData ->
            findNavController().navigate(
                ForecastListFragmentDirections.actionForecastListToForecastDetailFragment(
                    forecastData
                )
            )
        }

}