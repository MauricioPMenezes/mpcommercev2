package com.devsuperior.mpcommerce.controllers;

import com.devsuperior.mpcommerce.Services.ProductService;
import com.devsuperior.mpcommerce.dto.ProductDTO;
import com.devsuperior.mpcommerce.entities.Product;
import com.devsuperior.mpcommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable  Long id){

        ProductDTO dto=service.findById(id);
        return service.findById(id);

    }


}
