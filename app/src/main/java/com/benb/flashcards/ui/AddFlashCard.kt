package com.benb.flashcards.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.benb.flashcards.R
import com.benb.flashcards.databinding.FragmentAddFlashCardBinding

// This is the first fragment a user sees
// From this fragment a user can create new flash card content

class AddFlashCard : Fragment() {

    private var _binding: FragmentAddFlashCardBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: FlashCardViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View
    {
        _binding = FragmentAddFlashCardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            addFlashCardFragment = this@AddFlashCard
        }

        // save new flash card
        binding.saveButton.setOnClickListener {
            //TODO ADD SAVE LOGIC
        }

        // go to all cards fragment
        binding.allCardsButton.setOnClickListener {
            val action = AddFlashCardDirections.actionAddFlashCardToFlashCardList()
            this.findNavController().navigate(action)
        }

        // go to test screen
        binding.testButton.setOnClickListener {
            val action = AddFlashCardDirections.actionAddFlashCardToTest()
            this.findNavController().navigate(action)
        }
    }

}