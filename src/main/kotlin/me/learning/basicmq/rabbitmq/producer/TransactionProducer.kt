package me.learning.basicmq.rabbitmq.producer

import me.learning.basicmq.model.Transaction
import me.learning.basicmq.rabbitmq.QueueEnum
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class TransactionProducer(
    private val template: RabbitTemplate
) {
    val log = LoggerFactory.getLogger(javaClass)

    fun sendAsPendingTransaction(request: Transaction) {
        template.convertAndSend(QueueEnum.TRANSFER_PENDING_QUEUE, request)
        log.info("Message sent to rabbitmq")
    }

    fun sendAsSentTransaction(request: Transaction) {
        template.convertAndSend(QueueEnum.TRANSFER_SENT_QUEUE, request)
        log.info("Message sent to rabbitmq")
    }
}