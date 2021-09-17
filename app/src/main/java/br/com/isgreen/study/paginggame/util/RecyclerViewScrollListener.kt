package br.com.isgreen.study.paginggame.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Éverdes Soares on 11/18/2020.
 */

abstract class RecyclerViewScrollListener : RecyclerView.OnScrollListener() {

    private var oldY = 0

    abstract fun onScrolledToEnd()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
        if (layoutManager != null) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val isScrollingToEnd = dy < oldY
            oldY = dy

            if (isScrollingToEnd && visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                onScrolledToEnd()
            }
        }
    }
}