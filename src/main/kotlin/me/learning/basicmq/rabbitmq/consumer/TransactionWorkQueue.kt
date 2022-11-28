package me.learning.basicmq.rabbitmq.consumer

import me.learning.basicmq.model.Transaction
import me.learning.basicmq.rabbitmq.QueueEnum
import me.learning.basicmq.service.impl.helper.TransactionHelper
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class TransactionWorkQueue(
    private val transactionHelper: TransactionHelper
) {
    val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessageOne(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 1 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessageTwo(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 2 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessageFour(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 4 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessageFive(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 5 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessageSix(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 6 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessageSeven(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 7 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage8(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 8 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage9(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 9 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage10(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 10 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage11(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 11 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage12(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 12 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage13(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 13 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage14(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 14 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage15(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 15 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage16(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 16 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage17(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 17 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage18(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 18 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage19(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 19 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }

    @RabbitListener(queues = [QueueEnum.TRANSFER_SENT_QUEUE])
    @RabbitHandler
    fun consumerMessage20(response: Transaction) {
        log.info("Got Sent TRSX: ${response.id}, with instance: 20 and date time: ${LocalDateTime.now()}")
        transactionHelper.remove(response.id)
    }
}