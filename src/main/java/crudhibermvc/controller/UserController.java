package crudhibermvc.controller;


import crudhibermvc.entity.Role;
import crudhibermvc.entity.User;
import crudhibermvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pavel Tokarev, 19.05.2020
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser(HttpSession session, Model model) {
        User user = userService.findUserById((Long) session.getAttribute("id"));
        model.addAttribute("userForm", user);
        return "user";
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("userForm", new User());
        return "admin";
    }

    @GetMapping("/admin/update")
    public String updateUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             Model model) {
        User user = new User();
        user.setId(userId);
        model.addAttribute("userForm", user);
        return "update";
    }

    @GetMapping("/admin/addUser")
    public String addUser(Model model) {
        model.addAttribute("userForm", new User());
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@RequestParam(required = true, defaultValue = "") String userRole,
                          @ModelAttribute("userForm") @Valid User userForm) {
        Set<Role> roles = new HashSet<>();
        if (userRole.contains("ADMIN")) {
            roles.add(new Role(2L, "ROLE_ADMIN"));
        }
        if (userRole.contains("USER")) {
            roles.add(new Role(1L, "ROLE_USER"));
        }
        userForm.setRoles(roles);
        userService.saveUser(userForm);
        return "redirect:/admin";
    }

    @PostMapping("/admin/update")
    public String updateUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String userRole,
                             @ModelAttribute("userForm") @Valid User userForm) {
        Set<Role> roles = new HashSet<>();
        if (userRole.contains("ADMIN")) {
            roles.add(new Role(2L, "ROLE_ADMIN"));
        }
        if (userRole.contains("USER")) {
            roles.add(new Role(1L, "ROLE_USER"));
        }
        userForm.setId(userId);
        userForm.setRoles(roles);
        userService.updateUser(userForm);
        return "redirect:/admin";
    }


    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if (action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }
}
