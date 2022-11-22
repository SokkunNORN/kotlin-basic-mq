package me.learning.basicmq.controller.response

import me.learning.basicmq.model.Status

data class StatusMessage(
    val transactionStatus: Status,
    val status: String,
    val message: String
)
