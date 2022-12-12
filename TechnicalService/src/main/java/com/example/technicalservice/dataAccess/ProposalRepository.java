package com.example.technicalservice.dataAccess;

import com.example.technicalservice.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal,Long> {
}
