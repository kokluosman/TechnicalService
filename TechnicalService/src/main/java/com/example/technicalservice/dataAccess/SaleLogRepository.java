package com.example.technicalservice.dataAccess;

import com.example.technicalservice.model.SaleLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleLogRepository extends JpaRepository<SaleLog,Long> {
}
