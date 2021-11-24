package com.example.customer;

import org.springframework.data.jpa.repository.JpaRepository;

interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByCardnumber(String cardnumber);
    void deleteByCardnumber(String cardnumber);
}
