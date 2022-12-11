package com.example.technicalservice.dataAccess;

import com.example.technicalservice.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale,Long> {
    List<Sale> findAllByproductId(Long id);

    List<Sale> findAllByIsSold(boolean isSold);

    List<Sale> findAllByProductIdAndIsSold(Long id, boolean isSold);
}
