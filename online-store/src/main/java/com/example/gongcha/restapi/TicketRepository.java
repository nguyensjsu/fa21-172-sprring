package com.example.gongcha.restapi;

import org.springframework.data.jpa.repository.JpaRepository;

interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
}
