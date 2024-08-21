package com.mustapha.backend.project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    Project createProject(String name,String description,List<Integer> user_ids);

    Optional<Project> getProjectById(Integer id);

    List<Project> getAllProjects();

    Project updateProject(Integer id, ProjectUpdateRequest updatedProject);

    void deleteProject(Integer id);
}
