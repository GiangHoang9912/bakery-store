package com.example.bakery.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bakery.types.*;

@RestController
@RequestMapping("/api/health")
public class HealthController {

  @GetMapping
  public MyResponse checkHealth() {
    MyResponse myResponse = new MyResponse("server is running ...!");
    return myResponse;
  }
}
