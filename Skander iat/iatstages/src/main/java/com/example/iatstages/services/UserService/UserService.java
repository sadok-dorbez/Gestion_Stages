package com.example.iatstages.services.UserService;

import com.example.iatstages.enums.ERole;
import com.example.iatstages.entities.Role;
import com.example.iatstages.entities.User;
import com.example.iatstages.repositories.RoleRepository;
import com.example.iatstages.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    UserRepository userRepo;

    RoleRepository roleRepo;
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
    }

    @Override
    public String updateUser(Long id, User _user) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isEmpty()) {
            return "Invalid username.";
        }
        else {
            User user = userOptional.get();
            user.setUsername(_user.getUsername());
            user.setEmail(_user.getEmail());
            user.setNumtele(_user.getNumtele());
            user.setCountry(_user.getCountry());
            user.setActivated(_user.getActivated());

            Role[] array = _user.getRoles().toArray(new Role[0]);
            String role = array[0].getName().toString();
            System.out.println(role);
            Set<Role> roles = new HashSet<>();

            if (role == null) {
                Role userRole = roleRepo.findByName(ERole.ROLE_CANDIDAT)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                switch (role) {
                    case "ROLE_ADMIN" -> {
                        Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "ROLE_RECRUTEUR" -> {
                        Role opRole = roleRepo.findByName(ERole.ROLE_RECRUTEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(opRole);
                    }
                    case "ROLE_CANDIDAT" -> {
                        Role chefRole = roleRepo.findByName(ERole.ROLE_CANDIDAT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(chefRole);
                    }}
            }
            user.setRoles(roles);
            userRepo.save(user);
            return "User with id " + id + " has been successfully updated !";
        }
    }

    @Override
    public String deleteUser(Long id)
    {
        userRepo.deleteById(id);
        return "User with id " + id + " has been successfully deleted !";
    }

    @Override
    public String activate(Long id) {
        User user = getUser(id);
        if (!user.getActivated())
        {user.setActivated(true);
            userRepo.save(user);
            return "User with id " + id + " successfuly Activated!"; }
        else
            user.setActivated(false);
        userRepo.save(user);
        return "User with id " + id + " successfuly Desactivated!";

    }

}
