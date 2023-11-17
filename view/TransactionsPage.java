package view;

import controller.DatabaseHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class TransactionsPage extends JFrame {
    public TransactionsPage(int userId) {
        setTitle("Transactions");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.dispose();
        });
        add(backButton, BorderLayout.PAGE_END);

        String[] columnNames = {"ID", "User ID", "User Name", "Game ID", "Game Name", "Total Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        try {
            DatabaseHandler conn = new DatabaseHandler();
            conn.connect();
            String query = "SELECT t.id, t.user_id, u.name, t.game_id, g.name, g.price " +
                           "FROM Transactions t " +
                           "JOIN Users u ON t.user_id = u.id " +
                           "JOIN Games g ON t.game_id = g.id " +
                           "WHERE t.user_id = " + userId;
            ResultSet rs = conn.con.createStatement().executeQuery(query);

            while (rs.next()) {
                Vector<Object> data = new Vector<>();
                data.add(rs.getInt(1));
                data.add(rs.getInt(2));
                data.add(rs.getString(3));
                data.add(rs.getInt(4));
                data.add(rs.getString(5));
                data.add(rs.getInt(6));
                model.addRow(data);
            }
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.setVisible(true);
    }
}