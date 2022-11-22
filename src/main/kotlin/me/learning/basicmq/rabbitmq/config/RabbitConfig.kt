package me.learning.basicmq.rabbitmq.config

import me.learning.basicmq.rabbitmq.Enum.TRANSFER_EXCHANGE
import me.learning.basicmq.rabbitmq.Enum.TRANSFER_QUEUE
import me.learning.basicmq.rabbitmq.Enum.TRANSFER_ROUTING_KEY
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.Connection
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    @Bean
    fun queue(): Queue = Queue(TRANSFER_QUEUE)

    @Bean
    fun exchange(): TopicExchange = TopicExchange(TRANSFER_EXCHANGE)

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(TRANSFER_ROUTING_KEY)
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