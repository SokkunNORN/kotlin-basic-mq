package me.learning.basicmq.rabbitmq.producer

import me.learning.basicmq.rabbitmq.QueueEnum
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionProducer {
    @Autowired
    private val template: RabbitTemplate = RabbitTemplate()

    fun send(request: Any) {
        template.convertAndSend(QueueEnum.TRANSFER_QUEUE, request)
    }
}