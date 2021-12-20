package com.solucioneshr.soft.testreign.data

data class Title(
    val fullyHighlighted: Boolean,
    val matchLevel: String,
    val matchedWords: List<String>,
    val value: String
)