package com.mustapha.backend.project;

import com.mustapha.backend.user.User;
import com.mustapha.backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements IProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Project createProject(String name, String description, List<Integer> user_ids) {
        // Find all users by their IDs
        List<User> users = userRepository.findAllById(user_ids);

        if (users.isEmpty()) {
            throw new RuntimeException("No users found with the provided IDs");
        }

        // Create a new project and set its properties
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);

        // Add each user to the project, which also adds the project to the user's list
        for (User user : users) {
            project.addDeveloper(user);
        }

        // Save and return the project
        return projectRepository.save(project);
    }



    @Override
    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Integer id, Project updatedProject) {
        return projectRepository.findById(id).map(project -> {
            project.setName(updatedProject.getName());
            project.setDescription(updatedProject.getDescription());
            project.setDevelopers(updatedProject.getDevelopers());
            return projectRepository.save(project);
        }).orElseThrow(() -> new RuntimeException("Project not found with id " + id));
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }
}
