package com.devsuperior.mpcommerce.repositories;

import com.devsuperior.mpcommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
