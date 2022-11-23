package me.learning.basicmq.rabbitmq.config

import me.learning.basicmq.rabbitmq.QueueEnum.TRANSFER_QUEUE
import me.learning.basicmq.rabbitmq.consumer.TransactionConsumer
import me.learning.basicmq.rabbitmq.producer.TransactionProducer
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class RabbitConfig {

    @Bean
    fun queue(): Queue = Queue(TRANSFER_QUEUE)

    @Profile("receiver")
    @Bean
    fun receiver(): TransactionConsumer {
        return TransactionConsumer()
    }

    @Profile("sender")
    @Bean
    fun sender(): TransactionProducer {
        return TransactionProducer()
    }

    @Bean
    fun converter(): MessageConverter = Jackson2JsonMessageConverter()

    @Bean
    fun template(connectionFactory: ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate()
        rabbitTemplate.messageConverter = converter()
        rabbitTemplate.connectionFactory = connectionFactory

        return rabbitTemplate
    }
}