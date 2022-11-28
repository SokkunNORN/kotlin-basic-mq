package me.learning.basicmq.rabbitmq.consumer

import me.learning.basicmq.model.Transaction
import me.learning.basicmq.rabbitmq.QueueEnum.TRANSFER_PENDING_QUEUE
import me.learning.basicmq.rabbitmq.QueueEnum.TRANSFER_SENT_QUEUE
import me.learning.basicmq.service.impl.helper.TransactionHelper
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class TransactionConsumer(
    private val transactionHelper: TransactionHelper
) {
    val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = [TRANSFER_PENDING_QUEUE])
    @RabbitHandler
    fun consumerMessageOne(response: Transaction) {
        log.info("Get Pending TRSX: $response, instance #1")
        response.run {
            message = "$message, instance #1"
            transactionHelper.save(response)
        }
    }

    @RabbitListener(queues = [TRANSFER_PENDING_QUEUE])
    @RabbitHandler
    fun consumerMessageTwo(response: Transaction) {
        log.info("Get Pending TRSX: $response, instance #2")
        response.run {
            message = "$message, instance #2"
            transactionHelper.save(response)
        }
    }
}