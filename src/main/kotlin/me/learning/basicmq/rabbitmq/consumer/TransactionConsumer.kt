package me.learning.basicmq.rabbitmq.consumer

import me.learning.basicmq.model.Transaction
import me.learning.basicmq.rabbitmq.QueueEnum.TRANSFER_QUEUE
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
@RabbitListener(queues = [TRANSFER_QUEUE])
class TransactionConsumer {
    val log = LoggerFactory.getLogger(javaClass)

    @RabbitHandler
    fun consumerMessage(response: Transaction) {
        log.info("Got Message from queue: ${response}")
    }
}