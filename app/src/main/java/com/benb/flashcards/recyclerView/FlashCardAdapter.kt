package com.benb.flashcards.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.benb.flashcards.data.FlashCard
import com.benb.flashcards.databinding.ListViewItemBinding
import com.benb.flashcards.ui.FlashCardListFragment
import kotlin.coroutines.coroutineContext

class FlashCardAdapter (
    private val onItemClicked: (FlashCard) -> Unit,
): ListAdapter<FlashCard, FlashCardAdapter.FlashCardViewHolder>(DiffCallback) {

    class FlashCardViewHolder(private var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(flashCard: FlashCard) {
            binding.apply {
                question.text = flashCard.question
                question.setOnClickListener { answer.isVisible = true }
                answer.text = flashCard.answer
                answer.setOnClickListener { answer.isGone = true }
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FlashCard>() {
            override fun areItemsTheSame(oldItem: FlashCard, newItem: FlashCard): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FlashCard, newItem: FlashCard): Boolean {
                return  oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : FlashCardViewHolder {

        return FlashCardViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: FlashCardViewHolder, position: Int){
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

}