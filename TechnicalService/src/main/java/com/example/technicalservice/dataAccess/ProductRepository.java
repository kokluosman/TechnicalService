package com.example.technicalservice.dataAccess;

import com.example.technicalservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
