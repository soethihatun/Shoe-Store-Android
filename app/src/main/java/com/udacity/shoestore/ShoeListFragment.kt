package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private val viewModel by activityViewModels<ShoeListViewModel> { defaultViewModelProviderFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        viewModel.shoes.observe(viewLifecycleOwner) { shoes ->
            shoes.forEach { shoe ->
                val tvShoe = TextView(requireContext()).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    val vertical = resources.getDimensionPixelOffset(R.dimen.content_vertical_margin)
                    val horizontal = resources.getDimensionPixelOffset(R.dimen.activity_horizontal_margin)
                    setPadding(horizontal, vertical, horizontal, vertical)
                    text = shoe.name
                }
                binding.layoutShoeList.addView(tvShoe)
            }
        }

        binding.fabAddShoe.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}