package com.devsuperior.mpcommerce.dto;

import com.devsuperior.mpcommerce.entities.Product;
import jakarta.validation.constraints.*;

public class ProductDTO {


    private Long id;

    @Size(min=3 , max = 80 , message = "Nome precisa ter entre 3 e 80 caracteres")
    @NotBlank(message = "Campo Nome Requerido")
    private String  name;

    @Size(min=10 , message = "Descrição precisa ter no minimo 10 caracteres")
    @NotBlank(message = "Campo Requerido")
    private String description;

    @Positive(message = "o Preço deve ser positivo")
    private Double price;

    private String imgUrl;

    
    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();

    }

    public Long getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
