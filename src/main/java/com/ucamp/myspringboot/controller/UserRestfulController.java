package com.ucamp.myspringboot.controller;

import com.ucamp.myspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserRestfulController {
    private final UserService userService;


}
