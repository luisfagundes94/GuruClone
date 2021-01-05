package com.luisfelipe.guruclone.presentation.my_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luisfelipe.guruclone.R
import com.luisfelipe.guruclone.databinding.FragmentMyListBinding


class MyListFragment : Fragment(R.layout.fragment_my_list) {

    private var _binding: FragmentMyListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMyListBinding.bind(view)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}