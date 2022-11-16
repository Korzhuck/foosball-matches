package com.korzhuck.foosball.ui.rankings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.korzhuck.foosball.databinding.FragmentRankingsBinding
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
        val root: View = binding.root

        val textView: TextView = binding.textRankings
        viewModel.rankings.observe(viewLifecycleOwner) {
            textView.text = it.toString()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
