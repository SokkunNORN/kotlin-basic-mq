package me.learning.basicmq.enum

object Transfer {
    enum class CurrencyCode(code: String) {
        KHR("KHR"),
        USD("USD")
    }

    enum class StatusCode(code: String) {
        PENDING("Pending"),
        SEND("Send"),
        SUCCESS("Success"),
        FAILED("Failed")
    }
}