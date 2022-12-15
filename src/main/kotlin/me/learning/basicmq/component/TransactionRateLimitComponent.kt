package me.learning.basicmq.component

import io.github.bucket4j.Bandwidth
import io.github.bucket4j.Bucket
import me.learning.basicmq.controller.exception.TooManyRequestsException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.Duration

@Aspect
@Component
class TransactionRateLimitComponent {
    private val log = LoggerFactory.getLogger(javaClass)

    private val bucket = Bucket.builder()
        .addLimit(Bandwidth.simple(10, Duration.ofSeconds(1)))
        .build()

    @Around("@annotation(me.learning.basicmq.annotation.RateLimit)")
    fun limit(point: ProceedingJoinPoint): Any {
        log.info("checking rate limit...")
        if (!bucket.tryConsume(10)) throw TooManyRequestsException("")
        return point.proceed()
    }
}