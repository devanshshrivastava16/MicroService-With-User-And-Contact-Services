package com.phenom.controller;

import com.phenom.entity.User;
import com.phenom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);

        // CORRECTION: Added "/user/" to the path to match ContactController
        // http://contact-service/contact/user/1234
        List contacts = this.restTemplate.getForObject(
                "http://contact-service/contact/user/" + userId,
                List.class
        );

        user.setContacts(contacts);
        return user;
    }
}