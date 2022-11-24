package me.learning.basicmq.rabbitmq.consumer

import me.learning.basicmq.model.Transaction
import me.learning.basicmq.rabbitmq.QueueEnum.TRANSFER_PENDING_QUEUE
import me.learning.basicmq.service.impl.helper.TransactionHelper
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
@RabbitListener(queues = [TRANSFER_PENDING_QUEUE])
class TransactionConsumer(
    private val transactionHelper: TransactionHelper
) {
    val log = LoggerFactory.getLogger(javaClass)

    @RabbitHandler
    fun consumerMessage(response: Transaction) {
        log.info("Got Message from queue: $response")
        transactionHelper.save(response)
    }
}