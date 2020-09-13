package com.example.madlevel2task2

data class Question(
    val text: String,
    val isCorrect: Boolean
) {
    companion object {
        val list = listOf(
            Question("A 'val' and 'var' are the same.", false),
            Question("Mobile Application Development grants 12 ECTS.", true)
        )
    }
}