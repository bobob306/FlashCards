package com.benb.flashcards.recyclerView

import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.benb.flashcards.R
import com.benb.flashcards.data.FlashCard
import com.benb.flashcards.databinding.ListViewItemBinding

class FlashCardAdapter (
    private val onItemClicked: (FlashCard) -> Unit
): ListAdapter<FlashCard, FlashCardAdapter.FlashCardViewHolder>(DiffCallback) {

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
        val viewHolder = FlashCardViewHolder(
            ListViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: FlashCardViewHolder, position: Int){
        holder.bind(getItem(position))
    }


    class FlashCardViewHolder(
        private var binding: ListViewItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(flashCard: FlashCard) {
            binding.question.text = flashCard.question
            binding.answer.text = flashCard.answer
        }
    }

}