package com.mustapha.backend.form;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Integer> {
    // Custom query method to find forms by developer
    List<Form> findByDeveloperId(Integer developerId);

    // Custom query method to find forms by project
    List<Form> findByProjectId(Integer projectId);
}
