package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
