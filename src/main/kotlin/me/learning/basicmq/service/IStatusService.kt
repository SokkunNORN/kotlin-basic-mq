package me.learning.basicmq.service

import me.learning.basicmq.model.Status

interface IStatusService {
    fun findAll(): List<Status>
    fun findById(id: Long): Status
}