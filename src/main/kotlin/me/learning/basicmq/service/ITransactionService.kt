package me.learning.basicmq.service

import me.learning.basicmq.controller.request.TransactionRequest
import me.learning.basicmq.controller.response.TransactionResponse
import me.learning.basicmq.model.Transaction

interface ITransactionService {
    fun findAll(): List<TransactionResponse>
    fun findById(id: Long): Transaction
    fun send(request: TransactionRequest): TransactionResponse
    fun sign(): Boolean
    fun settlement(): Boolean
    fun deleteAll(): Boolean
}