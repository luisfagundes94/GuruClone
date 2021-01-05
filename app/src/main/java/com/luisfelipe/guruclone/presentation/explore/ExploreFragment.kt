package com.luisfelipe.guruclone.presentation.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.luisfelipe.guruclone.R
import com.luisfelipe.guruclone.databinding.FragmentExploreBinding

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentExploreBinding.bind(view)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}