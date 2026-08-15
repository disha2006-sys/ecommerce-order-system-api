package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.entity.OrderItem;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommerce.exception.InsufficientStockException;
import com.ecommerce.ecommerce.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order createOrder(Order order) {
        log.info("Creating order for user ID: {}", order.getUser().getId());
        User user = userRepository.findById(order.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException ("User not found"));

        order.setUser(user);
        // 2. Check Product + Stock
        double totalAmount=0;
        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            if (product.getStockQuantity() < item.getQuantity()) {
                throw new InsufficientStockException(
                        "Insufficient stock. Requested: "
                                + item.getQuantity()
                                + ", Available: "
                                + product.getStockQuantity()
                );
            }

            log.info(
                    "Updating stock for product ID: {}. Current stock: {}, Requested: {}",
                    product.getId(),
                    product.getStockQuantity(),
                    item.getQuantity()
            );

            totalAmount += item.getPrice() * item.getQuantity();
            // 3. Deduct stock
            product.setStockQuantity(
                    product.getStockQuantity() - item.getQuantity()
            );
            productRepository.save(product);
        }
        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }


}