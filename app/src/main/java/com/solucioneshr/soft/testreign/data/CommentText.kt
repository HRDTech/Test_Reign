package com.solucioneshr.soft.testreign.data

data class CommentText(
    val fullyHighlighted: Boolean,
    val matchLevel: String,
    val matchedWords: List<String>,
    val value: String
)