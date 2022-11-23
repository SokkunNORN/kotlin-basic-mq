package me.learning.basicmq.service.impl.helper

import me.learning.basicmq.model.Transaction
import me.learning.basicmq.rabbitmq.producer.TransactionProducer
import org.springframework.stereotype.Service

@Service
class TransactionHelper(
    private val transactionProducer: TransactionProducer
) {
    fun sendToRabbitmq(request: Transaction) {
        transactionProducer.send(request)
    }
}