package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import model.Games;
import model.User;
import controller.SystemFunction;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GameListPage extends JFrame {
    public GameListPage(User user) {
        setTitle("Game List");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JButton transactionsButton = new JButton("Transactions");
        transactionsButton.addActionListener(e -> {
            new TransactionsPage(user.getId());
        });
        add(transactionsButton, BorderLayout.PAGE_START);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new LoginPage();
        });
        add(backButton, BorderLayout.PAGE_END);

        JPanel gamePanelContainer = new JPanel();
        gamePanelContainer.setLayout(new BoxLayout(gamePanelContainer, BoxLayout.Y_AXIS));

        ArrayList<Games> games = SystemFunction.getAllGames();
        for (Games game : games) {
            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.X_AXIS));

            JTextField nameField = new JTextField(game.getName());
            nameField.setEditable(false);
            gamePanel.add(nameField);

            JTextField genreField = new JTextField(game.getGenre());
            genreField.setEditable(false);
            gamePanel.add(genreField);

            JTextField priceField = new JTextField(String.valueOf(game.getPrice()));
            priceField.setEditable(false);
            gamePanel.add(priceField);

            try {
                Image image = ImageIO.read(new URL(game.getImage()));
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                gamePanel.add(imageLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }

            gamePanel.add(Box.createHorizontalGlue()); 

            JButton buyButton = new JButton("Buy Game");
            buyButton.addActionListener(e -> {
                boolean alreadyBought = SystemFunction.checkIfGameAlreadyBought(user.getId(), game.getId());
                if (alreadyBought) {
                    JOptionPane.showMessageDialog(this, "You have already bought this game!");
                } else {
                    boolean success = SystemFunction.insertTransaction(user.getId(), game.getId());
                    JOptionPane.showMessageDialog(this, success ? "Purchase successful!" : "Purchase failed!");
                }
            });
            gamePanel.add(buyButton);

            gamePanelContainer.add(gamePanel);
        }

        JScrollPane scrollPane = new JScrollPane(gamePanelContainer);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}