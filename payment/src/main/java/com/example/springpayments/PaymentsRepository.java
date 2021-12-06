package com.example.springpayments;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
=======

// This will be AUTO IMPLEMENTED by Spring into a Bean called PaymentsRepository
// inherits CRUD repository
>>>>>>> 2e2c071e2a60c116a618e989394e1d531f0a4fe2
// CRUD refers Create, Read, Update, Delete

public interface PaymentsRepository extends JpaRepository<PaymentsCommand, Long> {

}