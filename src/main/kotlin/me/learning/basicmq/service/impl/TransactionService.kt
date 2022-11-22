package me.learning.basicmq.service.impl

import me.learning.basicmq.model.Transaction
import me.learning.basicmq.repository.TransactionRepository
import me.learning.basicmq.service.ITransactionService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class TransactionService(
    private val transactionRepo: TransactionRepository
) : ITransactionService {
    override fun findAll(): List<Transaction> = transactionRepo.findAll()

    override fun findById(id: Long): Transaction = transactionRepo.findById(id).orElseThrow {
        throw RuntimeException("Transaction Id: [$id] not found")
    }

    @Transactional
    override fun saveAllToPending(): Boolean {
        val all = findAll()
        all.map { it.status.id = 1 }
        transactionRepo.saveAll(all)
        return true
    }

    override fun settlement(): Boolean {
        val all = findAll()
//        delay
        all.map { it.status.id = 4 }
        transactionRepo.saveAll(all)
        return true
    }
}