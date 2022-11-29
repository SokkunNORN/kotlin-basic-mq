package me.learning.basicmq.service.impl

import me.learning.basicmq.controller.request.TransactionRequest
import me.learning.basicmq.controller.response.TransactionResponse
import me.learning.basicmq.controller.response.helper.issue
import me.learning.basicmq.enum.Transfer
import me.learning.basicmq.model.Transaction
import me.learning.basicmq.repository.TransactionRepository
import me.learning.basicmq.service.ITransactionService
import me.learning.basicmq.service.impl.helper.TransactionHelper
import me.learning.basicmq.service.impl.helper.getHash
import me.learning.basicmq.service.impl.helper.toResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import javax.transaction.Transactional

@Service
class TransactionService(
    private val repository: TransactionRepository,
    private val helper: TransactionHelper
) : ITransactionService {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun findAll(): List<Transaction> = repository.findAll()

    override fun findById(id: Long): Transaction = repository.findById(id).orElseThrow {
        throw RuntimeException("Transaction Id: [$id] not found")
    }

    @Transactional
    override fun send(request: TransactionRequest): TransactionResponse {
        if (request.currencyCode!! !in Transfer.CurrencyCode.values().map { it.name }) issue("Invalid Currency Code [USD, KHR]")
        if (request.amount!! <= BigDecimal.ZERO) issue("Amount cannot be lease or equal than 0.00")

        val transaction = Transaction(
            currencyCode = request.currencyCode,
            amount = request.amount,
            statusCode = Transfer.StatusCode.PENDING.name,
            message = "Transaction message #"
        )
        log.info("Transaction Hash: ${transaction.getHash()}")

        transaction.hash = transaction.getHash()?.let {
            if (repository.existsByHash(it)) issue("Duplicate request transaction")
            it
        }

        helper.sendToRabbitmq(transaction)

        return transaction.toResponse()
    }

    @Transactional
    override fun sign(): Boolean {
        val all = findAll()
        val pending = all.filter { it.statusCode == Transfer.StatusCode.PENDING.name }
        pending.map {
            val sent = Transaction(
                id = it.id,
                amount = it.amount,
                currencyCode = it.currencyCode,
                statusCode = Transfer.StatusCode.SEND.name
            )

            helper.saveToSent(sent)
        }

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
}