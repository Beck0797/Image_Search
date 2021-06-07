package com.beck.imageSearchApp.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.beck.imageSearchApp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val viewModel by viewModels<GalleryViewModel> ()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //it will observe photos live data
        //the parameter that we passed which is viweLifecyeOwner is important as it stops updating the UI when view of the
        //fragment is destroyed. It can happen when fragment is put into the backstack 
        viewModel.photos.observe(viewLifecycleOwner){
            //this function will be triggered whenever the value of this live photo changes. And we get passed new paging data
        }
    }
}