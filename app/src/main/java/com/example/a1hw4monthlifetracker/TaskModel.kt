package com.example.a1hw4monthlifetracker

import java.io.Serializable

data class TaskModel(
    val task: String,
    val regular: String,
    val date: String
):Serializable