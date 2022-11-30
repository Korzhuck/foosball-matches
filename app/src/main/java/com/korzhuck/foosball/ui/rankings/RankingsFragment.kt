package com.korzhuck.foosball.ui.rankings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.korzhuck.foosball.R
import com.korzhuck.foosball.databinding.FragmentRankingsBinding
import com.korzhuck.foosball.domain.usecase.SortOrder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingsFragment : Fragment() {

    private val viewModel : RankingsViewModel by viewModels()
    private var _binding: FragmentRankingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.ab_rankings, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
            when (menuItem.itemId) {
                R.id.sort -> {
                    true
                }
                else -> false
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRankingsBinding.inflate(inflater, container, false)
        requireActivity().addMenuProvider(menuProvider)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().removeMenuProvider(menuProvider)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(binding.rankingsView)
        binding.matches.setOnClickListener { viewModel.loadRankings(SortOrder.Matches) }
        binding.wins.setOnClickListener { viewModel.loadRankings(SortOrder.Wins) }

        viewModel.initialize()
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
    ) {
        val rankingsAdapter = RankingsAdapter()
        recyclerView.adapter = rankingsAdapter
        viewModel.rankings.observe(viewLifecycleOwner) {
            rankingsAdapter.rankings = it
        }
    }
}
