package com.geekbrains.myfirsttests

class T1 {

    fun canUserGoToBar(user: User): Boolean {
        return user.age >= 18
    }

}

data class User(
    val name: String,
    val age: Int
)