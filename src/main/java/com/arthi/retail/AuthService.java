
package com.arthi.retail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    // CHANGE THIS PASSWORD MANUALLY
    private static final String ADMIN_PASSWORD =
            "ADEAdmin@2026";

    public static boolean verifyAdmin(String password) {
        return ADMIN_PASSWORD.equals(password);
    }

    // First-time account creation
    public static boolean createAccount(
            String username, String password) throws Exception {

        String sql = """
            INSERT INTO users (username, password_hash)
            VALUES (?, ?)
        """;

        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));

            ps.executeUpdate();
            return true;
        }
    }

    // Login validation
    public static boolean login(
            String username, String password) throws Exception {

        String sql = "SELECT password_hash FROM users WHERE username = ?";

        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return BCrypt.checkpw(
                            password,
                            rs.getString("password_hash")
                    );
                }
            }
        }

        return false;
    }

    // Change username and password
    public static boolean updateCredentials(
            String username, String password) throws Exception {

        String sql = """
            UPDATE users
            SET username = ?,
                password_hash = ?,
                updated_at = CURRENT_TIMESTAMP
            WHERE id = 1
        """;

        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));

            return ps.executeUpdate() > 0;
        }
    }
}
