package com.test.dto;

import lombok.Data;

@Data
public class UserDTO {
	
    private Integer id;
    private String name;
    private String email;
    private String gender;
    private String status;
}
