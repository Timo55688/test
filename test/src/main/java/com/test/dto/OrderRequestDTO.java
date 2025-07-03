package com.test.dto;

import java.util.Map;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private String customerName;
    private Map<Integer, Integer> productQuantities;
}
