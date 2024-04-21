package com.example.iatstages.services.UserService;

import com.example.iatstages.entities.User;
import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    User getUser(Long id);

    String updateUser(Long id, User _user);

    String deleteUser(Long id);

    String activate(Long id);
}
