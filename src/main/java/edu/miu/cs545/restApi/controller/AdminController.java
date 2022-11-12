package edu.miu.cs545.restApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    public String GetMapping(){
        return "<h1>Welcome Admin!</h1>";
    }
}
