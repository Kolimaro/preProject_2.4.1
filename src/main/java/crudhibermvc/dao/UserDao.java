package crudhibermvc.dao;

import crudhibermvc.model.User;

import java.util.List;

/**
 * @author Pavel Tokarev, 25.04.2020
 */

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(Long id);

    void addUser(User user);

    void editUser(User user);

    void deleteUser(User user);
}
