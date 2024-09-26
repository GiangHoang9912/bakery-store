package com.example.bakery.database;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private final String url;
  private final String user;
  private final String password;

  public DatabaseConnection() {
    Dotenv dotenv = Dotenv.load();
    this.url = dotenv.get("DB_URL");
    this.user = dotenv.get("DB_USER");
    this.password = dotenv.get("DB_PASSWORD");
  }

  public Connection getConnection() throws SQLException {
    try {
      Class.forName("org.postgresql.Driver");
      System.out.println(url);
      System.out.println(user);
      System.out.println(password);
      return DriverManager.getConnection(url, user, password);
    } catch (ClassNotFoundException e) {
      throw new SQLException("PostgreSQL driver not found", e);
    }
  }

  public void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        System.err.println("Error closing connection: " + e.getMessage());
      }
    }
  }
}
