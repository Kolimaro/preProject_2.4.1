package crudhibermvc.controller;


import crudhibermvc.entity.User;
import crudhibermvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @author Pavel Tokarev, 19.05.2020
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("userForm", user);
        return "user";
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("userForm", new User());
        return "admin";
    }

    @GetMapping("/admin/addUser")
    public String addUser(Model model) {
        model.addAttribute("userForm", new User());
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@RequestParam(required = true, defaultValue = "") String userRole,
                          @ModelAttribute("userForm") @Valid User userForm, Model model) {
        userService.setRoles(userForm, userRole);
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "addUser";
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/update")
    public String updateUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             Model model) {
        User user = new User();
        user.setId(userId);
        model.addAttribute("userForm", user);
        return "update";
    }

    @PostMapping("/admin/update")
    public String updateUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String userRole,
                             @ModelAttribute("userForm") @Valid User userForm) {
        userForm.setId(userId);
        userService.setRoles(userForm, userRole);
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
