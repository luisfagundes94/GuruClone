package com.luisfelipe.guruclone.presentation.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luisfelipe.guruclone.R
import com.luisfelipe.guruclone.databinding.FragmentWalletBinding

class WalletFragment : Fragment(R.layout.fragment_wallet) {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWalletBinding.bind(view)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}