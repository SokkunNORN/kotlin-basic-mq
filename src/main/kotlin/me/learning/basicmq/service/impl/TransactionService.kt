package me.learning.basicmq.service.impl

import me.learning.basicmq.controller.request.TransactionRequest
import me.learning.basicmq.controller.response.TransactionResponse
import me.learning.basicmq.enum.Transfer
import me.learning.basicmq.helper.Extension.systemFormat
import me.learning.basicmq.model.Transaction
import me.learning.basicmq.repository.TransactionRepository
import me.learning.basicmq.service.ITransactionService
import me.learning.basicmq.service.impl.helper.TransactionHelper
import org.springframework.stereotype.Service
import java.math.BigDecimal
import javax.transaction.Transactional

@Service
class TransactionService(
    private val repository: TransactionRepository,
    private val helper: TransactionHelper
) : ITransactionService {
    override fun findAll(): List<Transaction> = repository.findAll()

    override fun findById(id: Long): Transaction = repository.findById(id).orElseThrow {
        throw RuntimeException("Transaction Id: [$id] not found")
    }

    @Transactional
    override fun send(request: TransactionRequest): TransactionResponse {
        if (request.currencyCode !in Transfer.CurrencyCode.values().map { it.name }) error("Invalid currencyCode")
        if (request.amount <= BigDecimal.ZERO) error("Invalid amount")
        val currencyCode = Transfer.CurrencyCode.values().find { it.name == request.currencyCode } ?: Transfer.CurrencyCode.KHR

        for (i in 0..10000) {
            val transaction = Transaction(
                currencyCode = request.currencyCode,
                amount = request.amount,
                statusCode = Transfer.StatusCode.PENDING.name
            )

            helper.sendToRabbitmq(transaction)
        }


        return TransactionResponse(
            currencyCode = request.currencyCode,
            amount = request.amount.systemFormat(currencyCode),
            statusCode = Transfer.StatusCode.PENDING.name
        )
    }

    @Transactional
    override fun sign(): Boolean {
        val all = findAll()
        val pending = all.filter { it.statusCode == Transfer.StatusCode.PENDING.name }
        val send = pending.map {
            it.statusCode = Transfer.StatusCode.SEND.name
            it
        }
        helper.saveToSent(send)

        return true
    }

    @Transactional
    override fun settlement(): Boolean {
        val all = findAll()
        repository.saveAll(all)
        return true
    }

    override fun deleteAll(): Boolean {
        repository.deleteAll()

        return true
    }

    private fun error(message: String): Nothing = throw RuntimeException(message)
}