package com.geekbrains.myfirsttests

import org.junit.Assert
import org.junit.Test

class UserTest {

    private val t1 = T1()

    @Test
    fun `проверяем что пользоваетель 20 лет может попасть в Клуб`() {
        val user = User("vlad", 21)
        Assert.assertTrue(t1.canUserGoToBar(user))
    }
}