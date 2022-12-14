package me.learning.basicmq.service.impl.helper

import me.learning.basicmq.model.Transaction
import me.learning.basicmq.rabbitmq.producer.TransactionProducer
import me.learning.basicmq.repository.TransactionRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class TransactionHelper(
    private val transactionProducer: TransactionProducer,
    private val repository: TransactionRepository
) {
    val log = LoggerFactory.getLogger(javaClass)

    fun sendToRabbitmq(request: Transaction) {
        transactionProducer.sendAsPendingTransaction(request)
    }
    fun saveToSent(request: Transaction) {
        transactionProducer.sendAsSentTransaction(request)
    }
    @Transactional
    fun save(request: Transaction) {
        repository.save(request)
        log.info("Saved new transaction.")
    }

    @Transactional
    fun remove(id: Long) {
        log.info("Transaction helper id: $id")
        repository.deleteById(id)
    }
}