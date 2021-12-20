package com.solucioneshr.soft.testreign.data

data class DataJson(
    val exhaustiveNbHits: Boolean,
    val exhaustiveTypo: Boolean,
    val hits: List<Hit>,
    val hitsPerPage: Int,
    val nbHits: Int,
    val nbPages: Int,
    val page: Int,
    val params: String,
    val processingTimeMS: Int,
    val query: String,
    val renderingContent: RenderingContent
)