package crudhibermvc.service;


import crudhibermvc.entity.Role;
import crudhibermvc.entity.User;
import crudhibermvc.dao.RoleDao;
import crudhibermvc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Pavel Tokarev, 19.05.2020
 */

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public List<User> allUsers() {
        return userDao.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDb = userDao.findByUsername(user.getUsername());
        if (userFromDb != null) {
            return false;
        }
        if (user.getRoles() == null) {
            user.setRoles(Collections.singleton(roleDao.findById(1L)));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    public void setRoles(User user, String userRole) {
        Set<Role> roles = new HashSet<>();
        if (userRole.toUpperCase().contains("ADMIN")) {
            roles.add(roleDao.findById(2L));
        }
        if (userRole.toUpperCase().contains("USER")) {
            roles.add(roleDao.findById(1L));
        }
        user.setRoles(roles);
    }

    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }

    public boolean deleteUser(Long userId) {
        User user = userDao.findById(userId);
        if (user != null) {
            userDao.deleteUser(user);
            return true;
        }
        return false;
    }

}
