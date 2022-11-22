package me.learning.basicmq.service.impl

import me.learning.basicmq.controller.response.StatusMessage
import me.learning.basicmq.model.Status
import me.learning.basicmq.rabbitmq.Enum.TRANSFER_EXCHANGE
import me.learning.basicmq.rabbitmq.Enum.TRANSFER_QUEUE
import me.learning.basicmq.rabbitmq.Enum.TRANSFER_ROUTING_KEY
import me.learning.basicmq.repository.StatusRepository
import me.learning.basicmq.service.IStatusService
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class StatusService(
    private val statusRepo: StatusRepository,
//    private val rabbitTemplate: RabbitTemplate
) : IStatusService {

    @Autowired
    private val template: RabbitTemplate = RabbitTemplate()
    override fun findAll(): List<Status> {
        val all = statusRepo.findAll()
        val status = all.firstOrNull() ?: throw RuntimeException("No status found")

        val statusMessage = StatusMessage(status, "PROCESS", "Set status successfully ${status.id}")
        template.convertAndSend(TRANSFER_EXCHANGE, TRANSFER_ROUTING_KEY, statusMessage)

        return all
    }

    override fun findById(id: Long): Status = statusRepo.findById(id).orElseThrow {
        throw RuntimeException("Status Id [$id] not found")
    }
}