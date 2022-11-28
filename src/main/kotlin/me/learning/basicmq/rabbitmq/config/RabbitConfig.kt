package me.learning.basicmq.rabbitmq.config

import me.learning.basicmq.rabbitmq.QueueEnum.TRANSFER_PENDING_QUEUE
import me.learning.basicmq.rabbitmq.QueueEnum.TRANSFER_SENT_QUEUE
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    @Bean
    fun queue(): Queue = Queue(TRANSFER_PENDING_QUEUE)

    @Bean
    fun queuePending(): Queue = Queue(TRANSFER_SENT_QUEUE)

    @Bean
    fun converter(): MessageConverter = Jackson2JsonMessageConverter()

//    # Consumer Class is initialize from config
//    @Bean
//    fun transactionQueueConsumer(): TransactionConsumer = TransactionConsumer()

    @Bean
    fun template(connectionFactory: ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate()
        rabbitTemplate.messageConverter = converter()
        rabbitTemplate.connectionFactory = connectionFactory

        return rabbitTemplate
    }
}