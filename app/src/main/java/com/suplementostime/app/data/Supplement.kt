package com.suplementostime.app.data

data class Source(val label: String, val url: String)

data class Supplement(
    val id: String,
    val name: String,
    val category: String,
    val aliases: List<String>,
    val summary: String,
    val properties: List<String>,
    val dose: String,
    val howToTake: List<String>,
    val timing: String,
    val cautions: List<String>,
    val sources: List<Source>,
)
