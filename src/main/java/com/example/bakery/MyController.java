package com.example.bakery;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bakery.types.*;
import com.example.bakery.database.DatabaseConnection;
import java.sql.Connection;


@RestController
@RequestMapping("/api")
public class MyController {
  @GetMapping("/health")
  public MyResponse health() {
    DatabaseConnection db = new DatabaseConnection();
    try {
      Connection connection =  db.getConnection();
      System.out.println(connection.toString());
      connection.close();
      return new MyResponse("server is running ...!");
    } catch (Exception e) {
      return new MyResponse(e.getMessage());
    }
  }
}
