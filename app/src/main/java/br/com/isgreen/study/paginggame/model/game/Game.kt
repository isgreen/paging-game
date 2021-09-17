package br.com.isgreen.study.paginggame.model.game

import android.os.Parcelable
import br.com.isgreen.study.paginggame.model.genre.Genre
import br.com.isgreen.study.paginggame.model.platform.Platform
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by Éverdes Soares on 11/16/2020.
 */

@Parcelize
data class Game(
    @field:Json(name = "background_image")
    val backgroundImage: String?,
    @field:Json(name = "genres")
    val genres: List<Genre>?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "description_raw")
    val descriptionRaw: String?,
    @field:Json(name = "playtime")
    val playtime: Int?,
    @field:Json(name = "rating")
    val rating: Double?,
    @field:Json(name = "released")
    val released: String?,
    @field:Json(name = "platforms")
    val platforms: List<Platform>?
) : Parcelable {
    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}



