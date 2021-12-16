package com.example.order;

import org.springframework.data.jpa.repository.JpaRepository;

interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Purchase findByOrderNumber(String orderNumber);
}
