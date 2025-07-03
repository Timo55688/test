package com.test.dto;

import lombok.Data;

@Data
public class PaginationDTO {
    private int page;
    private int pages;
    private int total;
    private int limit;
}
