package br.com.isgreen.study.paginggame.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.isgreen.study.paginggame.databinding.ActivityGameSeparatorItemBinding

class SeparatorViewHolder(
    private val binding: ActivityGameSeparatorItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(separatorText: String) {
        binding.tvDescription.text = separatorText
    }

    companion object {
        fun create(parent: ViewGroup): SeparatorViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ActivityGameSeparatorItemBinding.inflate(inflater, parent, false)
            return SeparatorViewHolder(binding)
        }
    }
}