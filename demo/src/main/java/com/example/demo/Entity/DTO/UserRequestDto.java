package com.example.demo.Entity.DTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    private Long id;
    private String firstName;

    private String lastName;
    private String email;
}
