package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping()
    public String startPageForAdmin(ModelMap model, @AuthenticationPrincipal UserDetails userDetail) {
        User user = userService.findByUsername(userDetail.getUsername());
        model.addAttribute("curUser", user);
        model.addAttribute("users", userService.getAll());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/saveUser")
    public String addUser(@ModelAttribute("newUser") User user) {
        userService.saveOrUpdate(user);
        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Integer id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/updateUser")
    public String updateUserInfo(@ModelAttribute("user") User user) {
        userService.saveOrUpdate(user);
        return "redirect:/admin";
    }
}
