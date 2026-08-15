package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
    @Query("SELECT SUM(o.totalAmount) FROM Order o")
    Double getTotalSales();
}