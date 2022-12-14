package com.korzhuck.foosball.ui.edit

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.korzhuck.foosball.R
import com.korzhuck.foosball.databinding.FragmentEditMatchResultBinding
import com.korzhuck.foosball.models.MatchResult
import com.korzhuck.foosball.models.Player
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditMatchResultFragment : Fragment() {
    private val args: EditMatchResultFragmentArgs by navArgs()
    private val viewModel: EditMatchResultViewModel by viewModels()

    private var _binding: FragmentEditMatchResultBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.ab_edit_match_result, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
            when (menuItem.itemId) {
                android.R.id.home -> findNavController().navigateUp()
                R.id.save -> {
                    viewModel.saveResult(
                        MatchResult(
                            id = args.matchResult?.id ?: 0,
                            player1 = Player(binding.player1.text.toString()),
                            score1 = binding.score1.text.toString().toInt(),
                            player2 = Player(binding.player2.text.toString()),
                            score2 = binding.score2.text.toString().toInt(),
                        )
                    )
                    findNavController().navigateUp()
                    true
                }
                R.id.delete -> {
                    viewModel.removeResult(args.matchResult)
                    findNavController().navigateUp()
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
        _binding = FragmentEditMatchResultBinding.inflate(inflater, container, false)
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
        args.matchResult?.let {
            binding.player1.setText(it.player1.name)
            binding.player2.setText(it.player2.name)
            binding.score1.setText(it.score1.toString())
            binding.score2.setText(it.score2.toString())
        }
    }
}
