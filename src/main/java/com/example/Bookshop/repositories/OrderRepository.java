package com.example.Bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bookshop.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
