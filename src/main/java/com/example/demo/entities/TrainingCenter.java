package com.example.demo.entities;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Center name should not be null or blank")
    @Size(max = 40, message = "Center name must be less than 40 characters")
    private String centerName;

    @NotBlank(message = "Center code should not be null or blank")
    @Column(unique = true, length = 12)
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center code must be exactly 12 alphanumeric characters")
    private String centerCode;

    @Embedded
    @NotNull(message = "Address should not be null")
    @Valid
    private Address address;

    private int studentCapacity;

    @ElementCollection
    @CollectionTable(name = "courses_offered", joinColumns = @JoinColumn(name = "training_center_id"))
    @Column(name = "course")
    private List<String> coursesOffered;

    @CreationTimestamp
    private Date createdOn = new Date();

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String contactEmail;

    @NotBlank(message = "Phone number should not be null or blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String contactPhone;

    // Getters and Setters
}

