package br.com.isgreen.study.paginggame.base

import androidx.lifecycle.LiveData

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

interface BaseContract {

    interface ViewModel {
        val message: LiveData<String>
        val loading: LiveData<Boolean>
    }

}