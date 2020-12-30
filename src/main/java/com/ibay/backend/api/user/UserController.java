package com.ibay.backend.api.user;

import com.ibay.backend.model.User;
import com.ibay.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

import static com.ibay.backend.security.ApplicationUserRole.USER;

@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String addUser(@Valid @NotNull @RequestBody User user) {
        return userService.addUser(user, USER);
    }

    @GetMapping(path = "{id}")
    public User getUserByID(@PathVariable("id") String id) {
        return userService.getUserByID(id);
    }

    @GetMapping
    public User getUserByParam(@RequestParam Map<String, String> params) {
        return userService.getUserByParam(params);
    }

    public Boolean deleteUserByID(String id) {
        return userService.deleteUserByID(id);
    }

    @PutMapping(path = "{id}")
    public Boolean updateUserByID(@PathVariable("id") String id, @RequestBody User updatedUser) {
        return userService.updateUserByID(id, updatedUser);
    }
}
