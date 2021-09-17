package br.com.isgreen.study.paginggame.model.platform

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by Éverdes Soares on 11/16/2020.
 */

@Parcelize
data class Platform(
    @field:Json(name = "platform")
    val platformDetail: PlatformDetail?
) : Parcelable