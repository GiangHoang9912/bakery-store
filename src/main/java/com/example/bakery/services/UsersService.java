package com.example.bakery.services;

import java.util.ArrayList;
import java.util.List;
import com.example.bakery.models.Users;

public class UsersService {
  public UsersService() {
    System.out.println("UsersService");
  }

  public List<Users> getUsers() {
    List<Users> users = new ArrayList<Users>();
    users.add(new Users("1", "Giang Hoang", "123456"));
    users.add(new Users("2", "Nguyen Van A", "123456"));

    return users;
  }

  public Users getUser(String id) {
    return new Users("1", "Giang Hoang", "123456");
  }
}
