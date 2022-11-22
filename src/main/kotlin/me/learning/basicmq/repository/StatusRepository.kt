package me.learning.basicmq.repository

import me.learning.basicmq.model.Status
import org.springframework.data.jpa.repository.JpaRepository

interface StatusRepository: JpaRepository<Status, Long>