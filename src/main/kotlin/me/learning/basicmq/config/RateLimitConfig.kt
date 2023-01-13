package me.learning.basicmq.config

import io.github.bucket4j.Bandwidth
import io.github.bucket4j.Bucket
import io.github.bucket4j.Refill
import me.learning.basicmq.controller.exception.TooManyRequestsException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Aspect
@Configuration
class RateLimitConfig {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        private const val CAPACITY: Long = 10
        private const val SECOND: Long = 1

        private const val INIT_TOKEN: Long = 1
    }

    // greedy ~ try to add token with no delay
    private val refill = Refill.greedy(INIT_TOKEN, Duration.ofSeconds(SECOND))

    private val bandwidth = Bandwidth.classic(CAPACITY, refill)

    // $INIT_TOKEN contains $CAPACITY request for every $SECOND
    private val bucket = Bucket.builder()
        .addLimit(bandwidth)
        .build()


    @Around("@annotation(me.learning.basicmq.annotation.RateLimit)")
    fun limit(point: ProceedingJoinPoint): Any {
        if (!bucket.tryConsume(1)) throw TooManyRequestsException("")
        return point.proceed()
    }
}