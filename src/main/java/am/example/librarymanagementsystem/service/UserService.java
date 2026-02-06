package am.example.librarymanagementsystem.service;

import am.example.librarymanagementsystem.model.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findByUsername(String username);


}
