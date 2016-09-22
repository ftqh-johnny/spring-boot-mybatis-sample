package com.ft.turorial.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ft.turorial.spring.boot.domain.User;
import com.ft.turorial.spring.boot.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("{id}")
    public User getUser(@PathVariable Integer id){
    	return userService.findOne(id);
    }
}
