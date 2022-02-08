package com.example.CaseModule4.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "productname"
        })
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50)
    private String productname;

    @Size(min = 3, max = 200)
    private String description;

    @Range(min = 1, max = 200000)
    private double price;

    @Column(columnDefinition = "default 0")
    private int liked;

    @Lob
    private String img;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(Long id, String productname, String description, double price, int liked, String img, Category category) {
        this.id = id;
        this.productname = productname;
        this.description = description;
        this.price = price;
        this.liked = liked;
        this.img = img;
        this.category = category;
    }
}
