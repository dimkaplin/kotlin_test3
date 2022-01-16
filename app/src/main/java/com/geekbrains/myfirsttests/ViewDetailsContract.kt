package com.geekbrains.myfirsttests

internal interface ViewDetailsContract : ViewContract {
    fun setCount(count: Int)
}

internal interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()

}

