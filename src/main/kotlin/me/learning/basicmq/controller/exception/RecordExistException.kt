package me.learning.basicmq.controller.exception

data class RecordExistException(val name: String) : RuntimeException(name)