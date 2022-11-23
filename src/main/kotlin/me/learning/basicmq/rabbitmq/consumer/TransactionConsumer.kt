package me.learning.basicmq.rabbitmq.consumer

import me.learning.basicmq.controller.response.TransactionResponse
import me.learning.basicmq.rabbitmq.QueueEnum.TRANSFER_QUEUE
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.converter.SimpleMessageConverter

@RabbitListener(queues = [TRANSFER_QUEUE])
class TransactionConsumer {
    val log = LoggerFactory.getLogger(javaClass)

    @RabbitHandler
    fun consumerMessage(response: TransactionResponse) {
        log.info("The Status Message from queue: ${response::class.java}")
    }
}