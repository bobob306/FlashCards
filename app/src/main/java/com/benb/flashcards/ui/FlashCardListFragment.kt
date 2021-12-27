package com.benb.flashcards.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.benb.flashcards.FlashCardApplication
import com.benb.flashcards.FlashCardViewModel
import com.benb.flashcards.FlashCardViewModelFactory
import com.benb.flashcards.R
import com.benb.flashcards.databinding.FragmentFlashCardListBinding
import com.benb.flashcards.recyclerView.FlashCardAdapter


class FlashCardListFragment : Fragment() {

    private val viewModel: FlashCardViewModel by activityViewModels{
        FlashCardViewModelFactory(
            (activity?.application as FlashCardApplication).database.flashCardDao()
        )
    }

    private var _binding: FragmentFlashCardListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentFlashCardListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FlashCardAdapter {
            //TODO navigate to individual card
        }

        binding.recyclerView.adapter = adapter

        viewModel.allFlashCards.observe(this.viewLifecycleOwner) {items ->
            items.let {
                adapter.submitList(it)
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.floatingActionButton.setOnClickListener {
            this.findNavController().navigate(FlashCardListFragmentDirections.actionFlashCardListFragmentToAddFlashCardFragment())
        }

    }


}