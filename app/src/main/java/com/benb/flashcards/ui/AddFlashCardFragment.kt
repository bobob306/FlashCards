package com.benb.flashcards.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.benb.flashcards.FlashCardApplication
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.benb.flashcards.FlashCardViewModel
import com.benb.flashcards.FlashCardViewModelFactory
import com.benb.flashcards.R
import com.benb.flashcards.data.FlashCard
import com.benb.flashcards.databinding.FragmentAddFlashCardBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Fragment for adding or updating flash cards in the database
 */

class AddFlashCardFragment : Fragment() {

    private val navigationArgs: AddFlashCardFragmentArgs by navArgs()

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
        return binding.root
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.questionET.text.toString(),
            binding.answerET.text.toString(),
        )
    }

    private fun clearETs() {
        binding.questionET.setText("")
        binding.answerET.setText("")
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
        val id = navigationArgs.flashCardId
        if (id > 0)
            viewModel.retrieveFlashCard(id).observe(this.viewLifecycleOwner) { selectedFlashCard ->
                flashCard = selectedFlashCard
                bind(flashCard)
            } else {
        binding.saveBtn.setOnClickListener {
            addNewItem()
            clearETs()
        }
        }
    }

    fun bind(flashCard: FlashCard) {
        binding.apply {
            questionET.setText(flashCard.question, TextView.BufferType.SPANNABLE)
            answerET.setText(flashCard.answer, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener { updateFlashCard() }
            deleteBtn.isVisible = true
            deleteBtn.setOnClickListener { showConfDialog() }
        }
    }

    private fun showConfDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage("Are you sure you want to delete")
            .setCancelable(false)
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes") { _, _ ->
                delete()
            }
            .show()

    }

    private fun delete() {
        viewModel.deleteCard(flashCard)
        findNavController().navigateUp()
    }

    private fun updateFlashCard() {
        if (isEntryValid()) {
            viewModel.updateFlashCard(
                this.navigationArgs.flashCardId,
                this.binding.questionET.text.toString(),
                this.binding.answerET.text.toString()
            )
            findNavController().navigateUp()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }


}