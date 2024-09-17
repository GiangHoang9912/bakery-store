package com.example.bakery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bakery.types.*;

@RestController
@RequestMapping("/api")
public class MyController {

  @GetMapping("/sayHello") // Thay đổi URL ở đây
  public MyResponse sayHello() {
    return new MyResponse("Hello, World!");
  }
}
