package com.example.bakery.controllers;

import java.util.List;
import com.example.bakery.models.Users;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bakery.services.UsersService;

@RestController
@RequestMapping("/api/users")
public class UsersController {
  private UsersService usersService;
  public UsersController() {
    System.out.println("UsersController");
    this.usersService = new UsersService();
  }
  @RequestMapping("/get")
  public List<Users> getUser() {
    List<Users> users = this.usersService.getUsers();
    return users;
  }
}
