package br.com.isgreen.study.paginggame.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import br.com.isgreen.study.paginggame.databinding.ActivityGameFooterItemBinding

/**
 * Created by Éverdes Soares on 06/30/2021.
 */

class GameLoadStateViewHolder(
    private val binding: ActivityGameFooterItemBinding,
    onRetryClickListener: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, onRetryClickListener: () -> Unit): GameLoadStateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ActivityGameFooterItemBinding.inflate(inflater, parent, false)

            return GameLoadStateViewHolder(binding, onRetryClickListener)
        }
    }

    init {
        binding.btRetry.setOnClickListener { onRetryClickListener.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.tvError.text = loadState.error.localizedMessage
        }

        binding.tvError.isVisible = loadState is LoadState.Error
        binding.btRetry.isVisible = loadState is LoadState.Error
        binding.progressBar.isVisible = loadState is LoadState.Loading
    }
}