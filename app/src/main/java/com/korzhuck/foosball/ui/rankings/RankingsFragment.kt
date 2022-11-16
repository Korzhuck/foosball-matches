package com.korzhuck.foosball.ui.rankings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRankingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(binding.rankingsView)
        binding.matches.setOnClickListener { viewModel.loadRankings(SortOrder.Matches) }
        binding.wins.setOnClickListener { viewModel.loadRankings(SortOrder.Wins) }
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
    ) {
        val rankingsAdapter = RankingsAdapter()
        recyclerView.adapter = rankingsAdapter
        viewModel.rankings.observe(viewLifecycleOwner) {
            rankingsAdapter.rankings = it
            rankingsAdapter.notifyDataSetChanged()
        }
    }
}
