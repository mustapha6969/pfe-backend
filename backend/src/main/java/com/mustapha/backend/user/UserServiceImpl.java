package com.mustapha.backend.user;

import com.mustapha.backend.form.Form;
import com.mustapha.backend.form.FormRepository;
import com.mustapha.backend.project.Project;
import com.mustapha.backend.project.ProjectRepository;
import com.mustapha.backend.repport.Repport;
import com.mustapha.backend.repport.RepportRepository;
import com.mustapha.backend.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final RepportRepository repportRepository;
    private final FormRepository formRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProjectRepository projectRepository, RepportRepository repportRepository, FormRepository formRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.repportRepository = repportRepository;
        this.formRepository = formRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User createUser(String firstname, String lastname, String email, String password, String role) {
        Role userRole;
        try {
            userRole = Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role);
        }
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(userRole);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Integer id,String firstname, String lastname, String email, String role) {
        Role userRole;
        try {
            userRole = Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role);
        }
        return userRepository.findById(id).map(user -> {
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmail(email);
           user.setRole(userRole);
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));

    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));

        // Remove the user from all projects where they are a developer
        List<Project> projectsAsDeveloper = user.getProjects();
        for (Project project : projectsAsDeveloper) {
            project.getDevelopers().remove(user);
            projectRepository.save(project);
        }

        // Nullify the security consultant in all projects where the user is the consultant
        List<Project> projectsAsConsultant = projectRepository.findProjectsBySecurityConsultant_id(id);
        for (Project project : projectsAsConsultant) {
            project.setSecurityConsultant_id(null);
            projectRepository.save(project);
        }

        // Remove the user from all forms where they are the developer
        List<Form> forms = formRepository.findByDeveloper(user);
        for (Form form : forms) {
            form.setDeveloper(null);
            formRepository.save(form);
        }

        // Remove the user from all reports where they are the consultant
        List<Repport> reports = repportRepository.findBySecurityConsultant(user);
        for (Repport report : reports) {
            report.setSecurityConsultant(null);
            repportRepository.save(report);
        }

        // Delete all tokens associated with the user
       /* List<Token> tokens = tokenRepository.findByUser(user);
        for (Token token : tokens) {
            tokenRepository.delete(token);
        }*/

        // Now delete the user
        userRepository.deleteById(id);
    }



    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
