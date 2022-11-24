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
        transactionProducer.send(request)
    }
    fun saveToSent(request: List<Transaction>) {
        request.map {
            transactionProducer.sendAsSentTransaction(it)
        }
    }
    @Transactional
    fun save(request: Transaction) {
        repository.save(request)
        log.info("Saved new transaction.")
    }
}