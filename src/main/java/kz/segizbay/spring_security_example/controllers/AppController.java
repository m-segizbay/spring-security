package kz.segizbay.spring_security_example.controllers;

import kz.segizbay.spring_security_example.models.Application;
import kz.segizbay.spring_security_example.services.AppService;
import kz.segizbay.spring_security_example.models.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
@RequiredArgsConstructor
public class AppController {
    private final AppService appService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring Security Example";
    }

    @GetMapping("/all-app")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public List<Application> allApp(){
        return appService.allApplicationList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Application findAppById(@PathVariable int id){
        return appService.getApplicationById(id);
    }

    @PostMapping("/new-user")
    public String addNewUser(@RequestBody MyUser user){
        appService.addUser(user);
        return "User is save";
    }
}
