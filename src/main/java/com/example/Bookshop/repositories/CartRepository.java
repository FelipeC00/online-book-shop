package com.example.Bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bookshop.entities.Cart;
import com.example.Bookshop.entities.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

  Cart findByUser(User user);

}
