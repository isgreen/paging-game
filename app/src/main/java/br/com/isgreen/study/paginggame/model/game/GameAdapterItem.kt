package br.com.isgreen.study.paginggame.model.game

import kotlin.math.round

/**
 * Created by Éverdes Soares on 07/08/2021.
 */

sealed class GameAdapterItem {
    data class GameItem(val game: Game) : GameAdapterItem() {
        val roundedRatingCount: Int get() = round(this.game.rating ?: 0.0).toInt()
    }

    data class SeparatorItem(val description: String) : GameAdapterItem()
}