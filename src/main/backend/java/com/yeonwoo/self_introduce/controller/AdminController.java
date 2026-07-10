package com.yeonwoo.self_introduce.controller;

import com.yeonwoo.self_introduce.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    public void admin() {
        adminService.admin();
    }


}
