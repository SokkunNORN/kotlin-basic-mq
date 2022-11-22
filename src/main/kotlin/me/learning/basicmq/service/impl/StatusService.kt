package me.learning.basicmq.service.impl

import me.learning.basicmq.model.Status
import me.learning.basicmq.repository.StatusRepository
import me.learning.basicmq.service.IStatusService
import org.springframework.stereotype.Service

@Service
class StatusService(
    private val statusRepo: StatusRepository
) : IStatusService {
    override fun findAll(): List<Status> = statusRepo.findAll()

    override fun findById(id: Long): Status = statusRepo.findById(id).orElseThrow {
        throw RuntimeException("Status Id [$id] not found")
    }
}