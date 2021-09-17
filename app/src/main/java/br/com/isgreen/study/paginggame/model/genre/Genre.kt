package br.com.isgreen.study.paginggame.model.genre

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Éverdes Soares on 11/16/2020.
 */

@Parcelize
data class Genre(
    @field:Json(name = "games_count")
    val gamesCount: Int?,
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "image_background")
    val imageBackground: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "slug")
    val slug: String?
) : Parcelable