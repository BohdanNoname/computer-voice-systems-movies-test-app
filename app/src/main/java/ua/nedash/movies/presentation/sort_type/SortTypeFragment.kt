package ua.nedash.movies.presentation.sort_type

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ua.nedash.movies.R
import ua.nedash.movies.databinding.FragmentSortTypeBinding
import ua.nedash.movies.view_model.CatalogViewModel


class SortTypeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSortTypeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<CatalogViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        _binding = FragmentSortTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnTitle.setOnClickListener {
            viewModel.setSortType(CatalogViewModel.SortType.TITLE)
            findNavController().popBackStack()
        }

        binding.btnReleaseDate.setOnClickListener {
            viewModel.setSortType(CatalogViewModel.SortType.RELEASE_DATE)
            findNavController().popBackStack()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}