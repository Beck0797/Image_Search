package com.beck.imageSearchApp.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.beck.imageSearchApp.data.UnsplashRepository

// dagger will inject this repository into this view model automatically when it's initiated
class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepository
    ): ViewModel() {

        private val currencyQuery = MutableLiveData(DEFAULT_QUERY)
        val photos= currencyQuery.switchMap {// this will be executed whenever the value of currect query changes
            queryString ->
            repository.getSearchResults(queryString).cachedIn(viewModelScope)//this stops the crash when we rotated the device because we can't load from the same paging data twice

        }

    fun searchPhotos(query : String){
        currencyQuery.value = query
    }

    companion object{
        private const val DEFAULT_QUERY = "cats" // that that is shown when app first opened
    }
}