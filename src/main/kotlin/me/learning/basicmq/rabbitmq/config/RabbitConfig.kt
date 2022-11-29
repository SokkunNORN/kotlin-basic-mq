package me.learning.basicmq.rabbitmq.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import me.learning.basicmq.rabbitmq.QueueEnum.TRANSFER_PENDING_QUEUE
import me.learning.basicmq.rabbitmq.QueueEnum.TRANSFER_SENT_QUEUE
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class RabbitConfig {

    @Bean
    fun queue(): Queue = Queue(TRANSFER_PENDING_QUEUE)

    @Bean
    fun queuePending(): Queue = Queue(TRANSFER_SENT_QUEUE)

    @Bean
    fun builder(): Jackson2ObjectMapperBuilder = Jackson2ObjectMapperBuilder()

    @Bean
    fun converter(): Jackson2JsonMessageConverter {
        val objectMapper: ObjectMapper = builder().createXmlMapper(false).build()
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true)
        return Jackson2JsonMessageConverter(objectMapper)
    }

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