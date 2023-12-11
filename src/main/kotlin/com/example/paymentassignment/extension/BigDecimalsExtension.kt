package com.example.paymentassignment.extension

/**
 * live template 으로 extension method 정의 하는 것도 고려
 * import com.example.paymentassignment.extension.plus
 * import com.example.paymentassignment.extension.times
 * import com.example.paymentassignment.extension.minus
 * import com.example.paymentassignment.extension.div
 */

import java.math.BigDecimal
import java.math.RoundingMode

suspend operator fun BigDecimal.plus(other: Number): BigDecimal = this.add(other.toBigDecimal())
suspend operator fun BigDecimal.minus(other: Number): BigDecimal = this.subtract(other.toBigDecimal())
suspend operator fun BigDecimal.times(other: Number): BigDecimal = this.multiply(other.toBigDecimal())
suspend operator fun BigDecimal.div(other: Number): BigDecimal = this.divide(other.toBigDecimal(), RoundingMode.HALF_EVEN)

suspend fun Number.toBigDecimal() =
    when (this) {
        is Int -> BigDecimal.valueOf(this.toLong())
        is Long -> BigDecimal.valueOf(this)
        is Float, is Double -> BigDecimal(this.toString())
        is BigDecimal -> this
        else -> throw IllegalArgumentException()
    }!!
