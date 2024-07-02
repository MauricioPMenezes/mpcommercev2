package com.devsuperior.mpcommerce.Services;

import com.devsuperior.mpcommerce.Services.exceptions.DatabaseException;
import com.devsuperior.mpcommerce.Services.exceptions.ResourceNotFoundException;
import com.devsuperior.mpcommerce.dto.ProductDTO;
import com.devsuperior.mpcommerce.entities.Product;
import com.devsuperior.mpcommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso não encontrado!"));
        return new ProductDTO(product);

    }
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
         Page<Product> result= repository.findAll(pageable);
         return result.map(x->new ProductDTO(x));

    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){

        Product  entity = new Product();
        CopyDtoToEntity(dto,entity);
        entity =repository.save(entity);
        return new ProductDTO(entity);

    }

    @Transactional
    public ProductDTO update(Long id,ProductDTO dto){
        try {
            Product  entity =repository.getReferenceById(id);
            CopyDtoToEntity(dto,entity);
            entity =repository.save(entity);
            return new ProductDTO(entity);
        }catch (EntityNotFoundException e){

            throw new ResourceNotFoundException("Recurso não encontrado");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void CopyDtoToEntity(ProductDTO dto, Product entity) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

    }




}
