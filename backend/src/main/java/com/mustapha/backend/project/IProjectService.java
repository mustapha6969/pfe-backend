package com.mustapha.backend.project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    Project createProject(Project project);

    Optional<Project> getProjectById(Integer id);

    List<Project> getAllProjects();

    Project updateProject(Integer id, Project project);

    void deleteProject(Integer id);
}
