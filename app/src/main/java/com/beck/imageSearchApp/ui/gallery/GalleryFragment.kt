package com.beck.imageSearchApp.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.beck.imageSearchApp.R
import com.beck.imageSearchApp.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val viewModel by viewModels<GalleryViewModel> ()

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding !! // double bang operator. it says it doesn't care if _binding is null just return not nullable type. If something went wrong it will throw null pointer exception

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //it will observe photos live data
        //the parameter that we passed which is viweLifecyeOwner is important as it stops updating the UI when view of the
        //fragment is destroyed. It can happen when fragment is put into the backstack

        _binding = FragmentGalleryBinding.bind(view)

        val adapter = UnsplashPhotoAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = UnsplashPhotoLoadStateAdapter{adapter.retry()}, // paging library function that knows how retry loading another page},
                footer = UnsplashPhotoLoadStateAdapter{adapter.retry()},
            )
        }

        viewModel.photos.observe(viewLifecycleOwner){
            //this function will be triggered whenever the value of this live photo changes. And we get passed new paging data
            adapter.submitData(viewLifecycleOwner.lifecycle, it)


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}