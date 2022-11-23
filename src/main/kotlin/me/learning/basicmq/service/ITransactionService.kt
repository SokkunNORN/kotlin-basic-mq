package me.learning.basicmq.service

import me.learning.basicmq.controller.request.TransactionRequest
import me.learning.basicmq.controller.response.TransactionResponse
import me.learning.basicmq.model.Transaction

interface ITransactionService {
    fun findAll(): List<Transaction>
    fun findById(id: Long): Transaction
    fun save(request: TransactionRequest): TransactionResponse
    fun settlement(): Boolean
}