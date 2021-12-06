package com.example.springpayments;

import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called PaymentsRepository
// inherits CRUD repository
// CRUD refers Create, Read, Update, Delete

public interface PaymentsRepository extends JpaRepository<PaymentsCommand, Long> {

}