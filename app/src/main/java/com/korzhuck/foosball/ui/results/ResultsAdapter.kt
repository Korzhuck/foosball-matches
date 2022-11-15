package com.korzhuck.foosball.ui.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.korzhuck.foosball.databinding.ListItemResultBinding
import com.korzhuck.foosball.models.MatchResult

class ResultsAdapter : RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {
    var results = listOf<MatchResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        MatchResultViewHolder(
            binding = ListItemResultBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = when (holder) {
        is MatchResultViewHolder -> holder.bind(results[position])
    }

    override fun getItemCount(): Int = results.size

    sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class MatchResultViewHolder(private val binding: ListItemResultBinding) : ViewHolder(binding.root) {
        fun bind(result: MatchResult) {
            binding.result = result
        }
    }

}
