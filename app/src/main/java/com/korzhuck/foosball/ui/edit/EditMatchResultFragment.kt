package com.korzhuck.foosball.ui.edit

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditMatchResultFragment : Fragment() {
    private var _binding: EditMatchResultFragment? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
}
