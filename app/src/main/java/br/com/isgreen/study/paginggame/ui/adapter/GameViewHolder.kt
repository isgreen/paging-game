package br.com.isgreen.study.paginggame.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.isgreen.study.paginggame.R
import br.com.isgreen.study.paginggame.databinding.ActivityGameItemBinding
import br.com.isgreen.study.paginggame.model.game.Game
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.math.round

class GameViewHolder(
    private val binding: ActivityGameItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var game: Game? = null

    companion object {
        fun create(parent: ViewGroup): GameViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ActivityGameItemBinding.inflate(inflater, parent, false)
            return GameViewHolder(binding)
        }
    }

    fun bind(game: Game?) {
        Glide.with(binding.ivGameBackground.context)
            .load(game?.backgroundImage)
            .apply(
                RequestOptions.circleCropTransform()
                    .centerCrop()
                    .error(R.drawable.ic_baseline_image)
            )
            .into(binding.ivGameBackground)

        binding.tvGameName.text = game?.name
        binding.tvGameCreated.text =
            binding.tvGameCreated.context?.getString(R.string.released_on, game?.released)
        binding.rbGameRating.rating = round(game?.rating ?: 0.0).toFloat()
    }

    init {
//        itemView.setOnClickListener {
//            val game = getItem(bindingAdapterPosition)
//            game?.let {
//                onItemClickListener?.invoke(game?.game)
//            }
//        }
    }
}