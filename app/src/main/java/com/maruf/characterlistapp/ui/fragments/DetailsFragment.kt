package com.maruf.characterlistapp.ui.fragments

import android.annotation.SuppressLint
import com.maruf.characterlistapp.R
import com.maruf.characterlistapp.databinding.FragmentDetailsBinding
import com.maruf.characterlistapp.model.CharacterModelItem
import com.maruf.characterlistapp.utils.BaseFragment
import com.maruf.characterlistapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override fun getFragmentView(): Int {
        return R.layout.fragment_details
    }

    @SuppressLint("SetTextI18n")
    override fun configUi() {
        if (arguments != null) {
            val character: CharacterModelItem =
                requireArguments().getParcelable(Constants.PARCEL_KEY)!!
            binding.detailsTextView.text =
                        "${character.name}" +
                        "\n${character.actor}\n" +
                        "${character.dateOfBirth}\n" +
                        "${character.hairColour}\n" +
                        "${character.eyeColour}\n" +
                        "${character.gender}\n" +
                        "${character.house}\n" +
                        "${character.hogwartsStudent}"
        }
    }
}