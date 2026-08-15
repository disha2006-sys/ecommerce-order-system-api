package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.OrderResponseDTO;
import com.ecommerce.ecommerce.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDTO toResponseDTO(Order order) {

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        return dto;
    }
}