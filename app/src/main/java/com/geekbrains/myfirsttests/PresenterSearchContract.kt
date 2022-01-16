package com.geekbrains.myfirsttests

internal interface PresenterSearchContract : PresenterContract {
    fun search(searchQuery: String)
}
