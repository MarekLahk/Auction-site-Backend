package com.ibay.backend.api.admin;

import com.ibay.backend.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/api/v1/user")
@RestController
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @DeleteMapping(path = "{id}")
    public void DeleteUserByID(@PathVariable("id") String id) {
        adminUserService.deleteUserByID(id);
    }
}
