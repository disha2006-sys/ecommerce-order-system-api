package com.ecommerce.ecommerce.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequestDTO {
    @NonNull
    private Long userId;
}
