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
        List<User> users = userRepository.findAllById(user_ids);

        if (users.isEmpty()) {
            throw new RuntimeException("No users found with the provided IDs");
        }

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);

        for (User user : users) {
            project.addDeveloper(user);
        }

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
    public Project updateProject(Integer id, ProjectUpdateRequest updatedProject) {
        return projectRepository.findById(id).map(project -> {
            if (updatedProject.getName() != null) {
                project.setName(updatedProject.getName());
            }

            if (updatedProject.getDescription() != null) {
                project.setDescription(updatedProject.getDescription());
            }

            if (updatedProject.getUserIdsToAdd() != null && !updatedProject.getUserIdsToAdd().isEmpty()) {
                List<User> usersToAdd = userRepository.findAllById(updatedProject.getUserIdsToAdd());
                for (User user : usersToAdd) {
                    project.addDeveloper(user);
                }
            }

            if (updatedProject.getUserIdsToRemove() != null && !updatedProject.getUserIdsToRemove().isEmpty()) {
                List<User> usersToRemove = userRepository.findAllById(updatedProject.getUserIdsToRemove());
                for (User user : usersToRemove) {
                    project.removeDeveloper(user);
                }
            }

            return projectRepository.save(project);
        }).orElseThrow(() -> new RuntimeException("Project not found with id " + id));
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }
}
