package com.example.paymentassignment.paymenthistory

import org.springframework.data.repository.kotlin.CoroutineCrudRepository


interface PaymentHistoryRepository : CoroutineCrudRepository<PaymentHistory, Long>
