package crudhibermvc.repository;


import crudhibermvc.entity.User;

import java.util.List;

/**
 * @author Pavel Tokarev, 19.05.2020
 */

public interface UserDao {

    User findByUsername(String username);

    User findById(Long id);

    List<User> findAll();

    void save(User user);

    void deleteUser(User user);

    void updateUser(User user);
}
