package com.beck.imageSearchApp.api

import com.beck.imageSearchApp.data.UnsplashPhoto

data class UnsplashResponse (
    val results: List<UnsplashPhoto>
        )