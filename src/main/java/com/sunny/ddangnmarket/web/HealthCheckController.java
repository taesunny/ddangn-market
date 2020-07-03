package com.sunny.ddangnmarket.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @RequestMapping("/api/v1/available")
    public String available(){
        return "It's available - product server";
    }

    @RequestMapping("/api/v1/checked-out")
    public String  checkedOut() {
        return "Checked out - product server";
    }
}
