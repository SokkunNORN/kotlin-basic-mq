package me.learning.basicmq.controller.exception

data class IdNotFoundException(var name: String, var id: Long) : RuntimeException("The $name id[$id]")