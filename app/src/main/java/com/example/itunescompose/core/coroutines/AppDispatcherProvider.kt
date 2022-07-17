package com.example.itunescompose.core.coroutines

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatcherProvider @Inject constructor() : DispatcherProvider {
    override val Default = Dispatchers.Default
    override val IO = Dispatchers.IO
    override val Main = Dispatchers.Main
}
