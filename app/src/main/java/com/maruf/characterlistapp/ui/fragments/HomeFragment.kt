package com.maruf.characterlistapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maruf.characterlistapp.ui.adapters.CharacterAdapter
import com.maruf.characterlistapp.databinding.FragmentHomeBinding
import com.maruf.characterlistapp.utils.Constants
import com.maruf.characterlistapp.utils.NetworkResult
import com.maruf.characterlistapp.ui.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val characterViewModel by viewModels<CharacterViewModel>()
    private val mAdapter by lazy { CharacterAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        requestApiData()

        return binding.root
    }

    private fun requestApiData() {
        Log.d("Online Api call", "readDatabase: ")
        characterViewModel.getCharacter()
        characterViewModel.charactersResponse.observe(viewLifecycleOwner) { response ->
            binding.progressBar.isGone = true
            when (response) {
                is NetworkResult.Success -> {
                    binding.recyclerView.adapter = mAdapter
                    response.data?.let {
                        Log.d("TAG", "requestApiData: ${it.results.size}")
                        mAdapter.updateList(it.results)
                    }

                    Toast.makeText(
                        requireContext(),
                        "response.data  ${response.data?.results?.size}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Error -> {
                    if (response.message == Constants.NO_INTERNET_CONNECTION_MGS) {
                        showErrorMgs()

                    } else {
                        hideErrorMgs()
                    }
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

                else -> {}
            }
        }
    }

    private fun showErrorMgs() {
        binding.errorImageView.visibility = View.VISIBLE
        binding.errorTextView.visibility = View.VISIBLE
    }

    private fun hideErrorMgs() {
        binding.errorImageView.visibility = View.GONE
        binding.errorTextView.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}