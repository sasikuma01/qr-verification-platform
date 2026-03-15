package com.qrplatform.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class DbCheckController {

    private final DataSource dataSource;

    public DbCheckController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/db-check")
    public String checkDb() {
        try (Connection connection = dataSource.getConnection()) {
            return "Connected to DB: " + connection.getMetaData().getURL();
        } catch (Exception e) {
            return "DB Connection Failed: " + e.getMessage();
        }
    }
}