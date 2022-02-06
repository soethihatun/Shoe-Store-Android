package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private val viewModel by activityViewModels<ShoeListViewModel> { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoeDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val name = binding.edtShoeName.text.toString()
            val size = binding.edtShoeSize.text.toString().toDoubleOrNull() ?: 0.0
            val company = binding.edtShoeCompany.text.toString()
            val description = binding.edtShoeDescription.text.toString()
            viewModel.addShoe(name, size, company, description)
        }

        binding.btnCancel.setOnClickListener { findNavController().popBackStack() }

        viewModel.shoeAdded.observe(viewLifecycleOwner) { added ->
            if (added) {
                findNavController().popBackStack()
                viewModel.shoeAddedComplete()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.errorMessageComplete()
            }
        }
    }
}