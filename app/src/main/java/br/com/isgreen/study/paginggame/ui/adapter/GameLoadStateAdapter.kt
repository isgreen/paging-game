package br.com.isgreen.study.paginggame.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 * Created by Éverdes Soares on 06/30/2021.
 */

class GameLoadStateAdapter(
    private val onRetryClickListener: () -> Unit
) : LoadStateAdapter<GameLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): GameLoadStateViewHolder {
        return GameLoadStateViewHolder.create(parent, onRetryClickListener)
    }

    override fun onBindViewHolder(holder: GameLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}