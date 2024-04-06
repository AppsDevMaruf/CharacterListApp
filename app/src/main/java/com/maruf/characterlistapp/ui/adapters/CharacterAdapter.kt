package com.maruf.characterlistapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maruf.characterlistapp.databinding.RowCharacterItemBinding
import com.maruf.characterlistapp.model.CharacterModelItem
import com.maruf.characterlistapp.utils.DiffCallback
import com.maruf.characterlistapp.utils.bindingAdapter.splitFullName

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {
    private var recipeList = emptyList<CharacterModelItem>()
    fun updateList(list: List<CharacterModelItem>) {
        val diffCallback = DiffCallback(recipeList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        recipeList = list
        diffResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(val binding: RowCharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterModelItem) {
            binding.character = character
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RowCharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recipe = recipeList[position]
        recipe.name?.splitFullName()
        holder.bind(recipe)

    }

    override fun getItemCount(): Int {
        Log.d("TAG", "getItemCount: ${recipeList.size}")
        return recipeList.size
    }


}