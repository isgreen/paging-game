package br.com.isgreen.study.paginggame.model.error

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

data class ErrorMessage(
    val message: String,
    val code: Int = -1
)