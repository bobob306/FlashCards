package com.benb.flashcards.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benb.flashcards.FlashCardsApplication
import com.benb.flashcards.databinding.FragmentFlashCardListBinding
import com.benb.flashcards.recyclerView.FlashCardAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FlashCardList: Fragment () {

    private var _binding: FragmentFlashCardListBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel: FlashCardViewModel by activityViewModels {
        FlashCardViewModelFactory(
            (activity?.application as FlashCardsApplication).database.cardDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlashCardListBinding.inflate(inflater, container, attachToParent = false)
        val view = binding.root
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.flashCardRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val flashCardAdapter = FlashCardAdapter
        recyclerView.adapter = FlashCardAdapter
        lifecycle.coroutineScope.launch{
            viewModel.allCards().collect() {
                flashCardAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}