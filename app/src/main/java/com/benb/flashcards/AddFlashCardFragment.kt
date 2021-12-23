package com.benb.flashcards

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.benb.flashcards.FlashCardApplication
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import com.benb.flashcards.data.FlashCard
import com.benb.flashcards.databinding.FragmentAddFlashCardBinding

/**
 * Fragment for adding or updating flash cards in the database
 */

class AddFlashCardFragment : Fragment() {

    private val viewModel: FlashCardViewModel by activityViewModels {
        FlashCardViewModelFactory(
            (activity?.application as FlashCardApplication).database.flashCardDao()
        )
    }

    lateinit var flashCard: FlashCard

    private var _binding: FragmentAddFlashCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFlashCardBinding.inflate(inflater, container, false)

        binding.saveBtn.setOnClickListener {
            addNewItem()
        }
        return binding.root
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.questionET.text.toString(),
            binding.answerET.text.toString(),
        )
    }

    private fun addNewItem() {
        if (isEntryValid()) {
            viewModel.addNewFlashCard(
                binding.questionET.text.toString(),
                binding.answerET.text.toString()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveBtn.setOnClickListener {
            addNewItem()
        }
    }

    /**
     * Unclear what the need is for this line
     * fun bind(flashCard: FlashCard) {
        binding.apply {
            questionET.setText(flashCard.question, TextView.BufferType.SPANNABLE)
            answerET.setText(flashCard.answer, TextView.BufferType.SPANNABLE)
        }
    }
    */

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }


}