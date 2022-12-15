package me.learning.basicmq.controller.api

import me.learning.basicmq.controller.request.TransactionRequest
import me.learning.basicmq.service.ITransactionService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transfer")
class TransactionController (
    private val service: ITransactionService
) {
    @GetMapping("/all")
    fun findAll() = service.findAll()
    @PostMapping
    fun save(@RequestBody request: TransactionRequest) = service.send(request)
    @GetMapping("/send")
    fun send() = service.sign()
    @DeleteMapping("/all")
    fun deleteAll() = service.deleteAll()
    @GetMapping("/settle")
    fun settle() = service.settlement()
}