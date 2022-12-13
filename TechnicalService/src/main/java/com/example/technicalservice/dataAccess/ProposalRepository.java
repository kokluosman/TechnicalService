package com.example.technicalservice.dataAccess;

import com.example.technicalservice.model.Proposal;
import com.example.technicalservice.model.ProposalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal,Long> {
    List<Proposal> findAllByUserId(long id);

    List<Proposal> findAllByProposalStatus(ProposalStatus status);
}
