package com.mustapha.backend.user;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createUser(String firstName, String lastName, String email, String password, String role);

    Optional<User> getUserById(Integer id);

    List<User> getAllUsers();

    User updateUser(Integer id,String firstName, String lastName, String email,String role);

    void deleteUser(Integer id);

    Optional<User> getUserByEmail(String email);
}
