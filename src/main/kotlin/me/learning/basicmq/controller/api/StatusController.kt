package me.learning.basicmq.controller.api

import me.learning.basicmq.service.impl.StatusService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transfer/status")
class StatusController(
    private val service: StatusService
) {

    @GetMapping
    fun getAll() = service.findAll()
}