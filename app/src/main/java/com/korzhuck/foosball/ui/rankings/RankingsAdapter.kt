package com.korzhuck.foosball.ui.rankings

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.korzhuck.foosball.databinding.ListItemRankingBinding
import com.korzhuck.foosball.models.PlayerRanking

class RankingsAdapter : RecyclerView.Adapter<RankingsAdapter.ViewHolder>() {
    var rankings = listOf<PlayerRanking>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        PlayerRankingViewHolder(
            binding = ListItemRankingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = when (holder) {
        is PlayerRankingViewHolder -> holder.bind(ranking = rankings[position])
    }

    override fun getItemCount(): Int = rankings.size

    sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class PlayerRankingViewHolder(private val binding: ListItemRankingBinding) :
        ViewHolder(binding.root) {
        fun bind(ranking: PlayerRanking) {
            binding.ranking = ranking
        }
    }
}
