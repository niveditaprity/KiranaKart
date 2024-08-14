package com.ecommerce.KiranaKart.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;
}
