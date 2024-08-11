package com.mustapha.backend.user;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createUser(User user);

    Optional<User> getUserById(Integer id);

    List<User> getAllUsers();

    User updateUser(Integer id, User user);

    void deleteUser(Integer id);

    Optional<User> getUserByEmail(String email);
}
