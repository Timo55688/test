package com.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResultDTO {
    private boolean success;
    private String message;
}

