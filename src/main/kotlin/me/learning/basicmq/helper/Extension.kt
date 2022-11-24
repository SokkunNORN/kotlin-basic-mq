package me.learning.basicmq.helper

import me.learning.basicmq.enum.Transfer
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

object Extension {
    fun BigDecimal.systemFormat(currencyCode: Transfer.CurrencyCode): String {
        val kh = DecimalFormat("#,###")
        kh.roundingMode = RoundingMode.DOWN
        val us = DecimalFormat("#,###.##")
        us.roundingMode = RoundingMode.FLOOR

        if (currencyCode == Transfer.CurrencyCode.KHR) return kh.format(this)
        return us.format(this)
    }
}