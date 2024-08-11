package com.mustapha.backend.project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    // Custom query methods can be added here if needed
}
