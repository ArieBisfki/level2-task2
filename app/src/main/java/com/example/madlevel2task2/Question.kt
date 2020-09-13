package com.example.madlevel2task2

data class Question(
    val statementText: String,
    val statementIsCorrect: Boolean
) {
    companion object {
        val list = arrayListOf(
            Question("A 'val' and 'var' are the same.", false),
            Question("Mobile Application Development grants 12 ECTS.", true)
        )
    }
}