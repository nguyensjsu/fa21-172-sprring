package com.example.onlinestore.restapi;

import org.springframework.data.jpa.repository.JpaRepository;

interface CardRepository extends JpaRepository<Card, String> {
    Card findByCardnumber(String cardnumber);
}
