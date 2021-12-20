package com.solucioneshr.soft.testreign.data

import java.util.ArrayList

data class Hit(
    val _highlightResult: HighlightResult?,
    val _tags: List<String>,
    val author: String,
    val comment_text: String,
    val created_at: String,
    val created_at_i: Int,
    val num_comments: Any,
    val objectID: String,
    val parent_id: Int,
    val points: Any,
    val story_id: Int,
    val story_text: Any,
    val story_title: String,
    val story_url: Any,
    val title: Any,
    val url: Any
){

    constructor(story_title: String) : this(
        _highlightResult = null,
        _tags = ArrayList(),
        author = "",
        comment_text = "",
        created_at = "",
        created_at_i = 0,
        num_comments = "",
        objectID = "",
        parent_id = 0,
        points = "",
        story_id = 0,
        story_text = "",
        story_title = "",
        story_url = "",
        title = "",
        url = "" ){

    }
}

