package br.com.isgreen.study.paginggame.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.isgreen.study.paginggame.model.game.Game
import br.com.isgreen.study.paginggame.model.game.GameAdapterItem
import br.com.isgreen.study.paginggame.ui.SeparatorViewHolder

/**
 * Created by Éverdes Soares on 11/16/2020.
 */

class GameAdapter :
    PagingDataAdapter<GameAdapterItem, RecyclerView.ViewHolder>(GAME_ADAPTER_ITEM_COMPARATOR) {

    companion object {
        private const val ITEM_TYPE_GAME = 0
        private const val ITEM_TYPE_SEPARATOR = 1

        private val GAME_ADAPTER_ITEM_COMPARATOR =
            object : DiffUtil.ItemCallback<GameAdapterItem>() {
                override fun areItemsTheSame(
                    oldItem: GameAdapterItem,
                    newItem: GameAdapterItem
                ): Boolean {
                    return (oldItem is GameAdapterItem.GameItem
                            && newItem is GameAdapterItem.GameItem
                            && oldItem.game.id == newItem.game.id)
                            ||
                            (oldItem is GameAdapterItem.SeparatorItem
                                    && newItem is GameAdapterItem.SeparatorItem
                                    && oldItem.description == newItem.description)
                }

                override fun areContentsTheSame(
                    oldItem: GameAdapterItem,
                    newItem: GameAdapterItem
                ): Boolean = oldItem == newItem
            }
    }

    var onItemClickListener: ((game: Game) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE_GAME) {
            GameViewHolder.create(parent)
        } else {
            SeparatorViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val gameAdapterItem = getItem(position)) {
            is GameAdapterItem.GameItem ->
                (holder as GameViewHolder).bind(gameAdapterItem.game)
            is GameAdapterItem.SeparatorItem ->
                (holder as SeparatorViewHolder).bind(gameAdapterItem.description)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is GameAdapterItem.GameItem -> ITEM_TYPE_GAME
            is GameAdapterItem.SeparatorItem -> ITEM_TYPE_SEPARATOR
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }
}
