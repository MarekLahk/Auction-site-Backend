package com.ibay.backend.api.admin;

import com.ibay.backend.model.User;
import com.ibay.backend.security.ApplicationUserRole;
import com.ibay.backend.service.AdminUserService;
import com.ibay.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/api/v1/user")
@RestController
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final UserService userService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService, UserService userService) {
        this.adminUserService = adminUserService;
        this.userService = userService;
    }

    @DeleteMapping(path = "{id}")
    public void DeleteUserByID(@PathVariable("id") String id) {
        adminUserService.deleteUserByID(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return adminUserService.getAllUsers();
    }

    @PostMapping
    public String addNewAdmin(@RequestBody User user) {
        return userService.addUser(user, ApplicationUserRole.ADMIN);
    }

}
