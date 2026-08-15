package com.ecommerce.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDTO {
    private Long orderId;
    private Long userId;
    private double totalAmount;
    private String status;
}
