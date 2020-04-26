package crudhibermvc.controller;

import crudhibermvc.model.User;
import crudhibermvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Pavel Tokarev, 25.04.2020
 */

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String allUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("usersFromDB", users);
        return "allUsers";
    }

    @GetMapping(value = "/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping(value = "/addUser")
    public String addUserProcess(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/updateUser/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping(value = "/updateUser")
    public String updateUserProcess(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/";
    }

    @PostMapping(value = "/deleteUser/{id}")
    public String deleteUserProcess(@PathVariable("id") Long id) {
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/";
    }

}
