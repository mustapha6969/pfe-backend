package com.mustapha.backend.project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT p from Project p where p.securityConsultant_id = :id")
    List<Project> findProjectsBySecurityConsultant_id(Integer id);
    // Custom query methods can be added here if needed
}
