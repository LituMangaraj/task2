package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name="details")
@Data
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "ParentId cannot be null")
    private Long parentId;
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name must have at least 1 character")
    private String name;
    @NotNull(message = "Color cannot be null")
    private String color;
}
