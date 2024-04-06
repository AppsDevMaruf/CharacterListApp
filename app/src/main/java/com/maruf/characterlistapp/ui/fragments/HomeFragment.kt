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
import androidx.navigation.fragment.findNavController
import com.maruf.characterlistapp.R
import com.maruf.characterlistapp.ui.adapters.CharacterAdapter
import com.maruf.characterlistapp.databinding.FragmentHomeBinding
import com.maruf.characterlistapp.model.CharacterModelItem
import com.maruf.characterlistapp.ui.interfaces.SelectedCharacterDetails
import com.maruf.characterlistapp.utils.Constants
import com.maruf.characterlistapp.utils.NetworkResult
import com.maruf.characterlistapp.ui.viewmodel.CharacterViewModel
import com.maruf.characterlistapp.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(),SelectedCharacterDetails {

    private val characterViewModel by viewModels<CharacterViewModel>()
    private val mAdapter by lazy { CharacterAdapter(this) }
    var bundle = Bundle()
    override fun getFragmentView(): Int {
        return R.layout.fragment_home
    }

    override fun binObserver() {
        requestApiData()
    }

    private fun requestApiData() {
        characterViewModel.getCharacter()
        characterViewModel.charactersResponse.observe(viewLifecycleOwner) { response ->
            binding.progressBar.isGone = true
            when (response) {
                is NetworkResult.Success -> {
                    binding.recyclerView.adapter = mAdapter
                    response.data?.let {
                        Log.d("TAG", "requestApiData: ${it.size}")
                        mAdapter.updateList(it)
                    }

                }

                is NetworkResult.Error -> {
                    if (response.message == Constants.NO_INTERNET_CONNECTION_MGS) {
                        showErrorMgs()

                    } else {
                        hideErrorMgs()
                    }
                    Log.d("TAG", "getRecipesSafeCall: ${response.message}")
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

    override fun selectedCharacter(character: CharacterModelItem) {
        bundle.putParcelable(Constants.PARCEL_KEY, character) // Key, value
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }


}