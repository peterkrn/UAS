package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Games;
import model.Transactions;
import model.User;

public class SystemFunction {
    static DatabaseHandler conn = new DatabaseHandler();

    public static boolean checkEmailPass(String email, String pass) {
        conn.connect();
        String query = "SELECT * FROM users";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (email.equals(rs.getString("email")) && pass.equals(rs.getString("password"))) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkPassAuth(String pass) {
        if (pass.length() < 8) {
            return false;
        }
        return true;
    }

    public static User getUserByEmail(String email) {
        conn.connect();
        String query = "SELECT * FROM users WHERE email='" + email + "'";
        try {
            User user = new User();
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Games> getAllGames() {
        ArrayList<Games> games = new ArrayList<>();

        try {
            conn.connect();
            String query = "SELECT * FROM games";
            ResultSet rs = conn.con.createStatement().executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String genre = rs.getString("genre");
                int price = rs.getInt("price");
                String image = rs.getString("image");

                Games game = new Games(id, name, genre, price, image);
                games.add(game);
            }

            rs.close();
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }

    public static ArrayList<Transactions> getAllTransactions() {
        ArrayList<Transactions> transactions = new ArrayList<>();

        try {
            conn.connect();
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transactions");

            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int gameId = rs.getInt("game_id");

                Transactions transaction = new Transactions(id, userId, gameId);

                transactions.add(transaction);
            }

            rs.close();
            stmt.close();
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public static boolean insertTransaction(int userId, int gameId) {
        try {
            conn.connect();
            PreparedStatement pstmt = conn.con
                    .prepareStatement("INSERT INTO Transactions (user_id, game_id) VALUES (?, ?)");
            pstmt.setInt(1, userId);
            pstmt.setInt(2, gameId);
            int affectedRows = pstmt.executeUpdate();
            conn.disconnect();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkIfGameAlreadyBought(int userId, int gameId) {
        ArrayList<Transactions> transactions = getAllTransactions();

        for (Transactions transaction : transactions) {
            if (transaction.getUserId() == userId && transaction.getGameId() == gameId) {
                return true;
            }
        }
        return false;
    }
}