package com.example.madlevel2task2

data class Question(
    val statementText: String,
    val statementIsCorrect: Boolean
) {
    companion object {
        val list = arrayListOf(
            Question("A 'val' and 'var' are the same.", false),
            Question("Mobile Application Development grants 12 ECTS.", false),
            Question("A Unit in Kotlin corresponds to a void in Java.", true),
            Question("In Kotlin 'when' replaces the 'switch' operator in Java.", true)
        )
    }
}