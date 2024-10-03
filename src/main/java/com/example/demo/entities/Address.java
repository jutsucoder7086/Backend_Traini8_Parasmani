package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NotBlank(message = "Detailed address should not be null or blank")
    private String detailedAddress;

    @NotBlank(message = "City should not be null or blank")
    private String city;

    @NotBlank(message = "State should not be null or blank")
    private String state;

    @NotBlank(message = "Pin code should not be null or blank")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pin-code should contain 6 digits")
    private String pincode;

    // Getters and Setters
}
