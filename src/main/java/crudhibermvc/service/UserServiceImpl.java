package crudhibermvc.service;

import crudhibermvc.dao.UserDao;
import crudhibermvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Pavel Tokarev, 25.04.2020
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
