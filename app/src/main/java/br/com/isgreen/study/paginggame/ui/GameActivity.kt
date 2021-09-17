package br.com.isgreen.study.paginggame.ui

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import br.com.isgreen.study.paginggame.R
import br.com.isgreen.study.paginggame.base.BaseActivity
import br.com.isgreen.study.paginggame.databinding.ActivityGameBinding
import br.com.isgreen.study.paginggame.model.game.Game
import br.com.isgreen.study.paginggame.ui.adapter.GameAdapter
import br.com.isgreen.study.paginggame.ui.adapter.GameLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Éverdes Soares on 11/16/2020.
 */

class GameActivity : BaseActivity() {

    private val adapter = GameAdapter()
    private val viewModel: GameViewModel by viewModel()
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        getGames()
    }

    private fun initView() {
        adapter.onItemClickListener = { game ->
            showGameDetail(game)
        }

        binding.rvGame.adapter = adapter.withLoadStateFooter(
            GameLoadStateAdapter(
                onRetryClickListener = {
                    adapter.retry()
                }
            )
        )

        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0) {
                showPlaceholderView(getString(R.string.no_data))
            }

            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.placeholderView.isVisible = loadState.source.refresh is LoadState.Error

//            val errorState = loadState.source.append as? LoadState.Error
//                ?: loadState.source.prepend as? LoadState.Error
//                ?: loadState.append as? LoadState.Error
//                ?: loadState.prepend as? LoadState.Error
//
//            errorState?.error?.localizedMessage?.let {
//                showPlaceholderView(it)
//            }
        }

        binding.placeholderView.onRetryClickListener = {
            adapter.retry()
        }
    }

    private fun getGames() {
        lifecycleScope.launch {
            viewModel.getGames().collectLatest {
                adapter.submitData(it)
            }
        }
//        viewModel.getGamesLiveData().observe(this@GameActivity) {
//            lifecycleScope.launch {
//                adapter.submitData(it)
//            }
//        }
    }

    private fun showPlaceholderView(message: String) {
        binding.placeholderView
            .text(message)
            .show()
    }

    private fun showGameDetail(game: Game) {
        Toast.makeText(this, "In development", Toast.LENGTH_SHORT).show()
    }
}