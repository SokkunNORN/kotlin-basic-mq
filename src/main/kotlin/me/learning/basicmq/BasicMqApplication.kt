package me.learning.basicmq

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BasicMqApplication

fun main(args: Array<String>) {
	runApplication<BasicMqApplication>(*args)
}
