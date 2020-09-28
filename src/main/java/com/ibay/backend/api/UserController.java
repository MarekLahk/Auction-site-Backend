package com.ibay.backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibay.backend.model.User;
import com.ibay.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    private final UserService userService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Boolean addUser(@Valid @NotNull @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping(path = "{id}")
    public User getUserByID(@PathVariable("id") String id) {
        return userService.getUserByID(id);
    }

    @DeleteMapping(path = "{id}")
    public Boolean deleteUserByID(@PathVariable("id") String id) {
        return userService.deleteUserByID(id);
    }

    @PutMapping(path = "{id}")
    public Boolean updateUserByID(@PathVariable("id") String id, @RequestBody User updatedUser) {
        return userService.updateUserByID(id, updatedUser);
    }
}
