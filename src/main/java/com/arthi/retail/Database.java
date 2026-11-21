
package com.arthi.retail;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {

    private static final String DB_FOLDER =
            Path.of(
                System.getenv("LOCALAPPDATA") != null
                    ? System.getenv("LOCALAPPDATA")
                    : System.getProperty("user.home"),
                "ArthiRetail"
            ).toString();

    private static final String DB_PATH =
            Path.of(DB_FOLDER, "capsule.db").toString();

    private static final String URL =
            "jdbc:sqlite:" + DB_PATH;

    public static void initialize() throws Exception {

        Files.createDirectories(Path.of(DB_FOLDER));

        try (Connection con = connect();
             Statement stmt = con.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL UNIQUE,
                    password_hash TEXT NOT NULL,
                    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
                    updated_at TEXT DEFAULT CURRENT_TIMESTAMP
                )
            """);
        }

        System.out.println("Database initialized: " + DB_PATH);
    }

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL);
    }

    public static boolean userExists() throws Exception {

        try (Connection con = connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT COUNT(*) FROM users")) {

            return rs.next() && rs.getInt(1) > 0;
        }
    }
}
