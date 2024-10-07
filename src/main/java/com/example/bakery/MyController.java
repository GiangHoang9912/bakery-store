package com.example.bakery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bakery.types.*;

@RestController
@RequestMapping("/api")
public class MyController {
  @GetMapping("/health")
  public MyResponse health() {
    return new MyResponse("server is running ...!");
  }
}
