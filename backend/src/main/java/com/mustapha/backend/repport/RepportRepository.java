package com.mustapha.backend.repport;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepportRepository extends JpaRepository<Repport, Integer> {
    // Custom query method to find reports by consultant
    List<Repport> findBySecurityConsultantId(Integer consultantId);

    // Custom query method to find reports by project
    List<Repport> findByProjectId(Integer projectId);
}
