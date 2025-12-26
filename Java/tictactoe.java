import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {
    private String player1Name;
    private String player2Name;
    private Color xColor;
    private Color oColor;
    private JButton[][] buttons = new JButton[3][3];
    private boolean xTurn = true;
    private int moveCount = 0;
    private JLabel statusLabel;

    public TicTacToe() {
        setupGame();
        initializeUI();
    }

    private void setupGame() {
        // Get player names
        player1Name = JOptionPane.showInputDialog(null, "Enter Player 1 name (X):", "Player 1", JOptionPane.QUESTION_MESSAGE);
        if (player1Name == null || player1Name.trim().isEmpty()) {
            player1Name = "Player 1";
        }

        player2Name = JOptionPane.showInputDialog(null, "Enter Player 2 name (O):", "Player 2", JOptionPane.QUESTION_MESSAGE);
        if (player2Name == null || player2Name.trim().isEmpty()) {
            player2Name = "Player 2";
        }

        // Get X color
        xColor = JColorChooser.showDialog(null, "Choose color for X (" + player1Name + ")", Color.BLUE);
        if (xColor == null) {
            xColor = Color.BLUE;
        }

        // Get O color
        oColor = JColorChooser.showDialog(null, "Choose color for O (" + player2Name + ")", Color.RED);
        if (oColor == null) {
            oColor = Color.RED;
        }
    }

    private void initializeUI() {
        setTitle("Tic Tac Toe - " + player1Name + " vs " + player2Name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status panel
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(new Color(210, 180, 140)); // Tan color
        statusLabel = new JLabel(player1Name + "'s turn (X)");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.NORTH);

        // Game board panel
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(5));
                
                // Draw grid lines
                int w = getWidth();
                int h = getHeight();
                g2d.drawLine(w/3, 0, w/3, h);
                g2d.drawLine(2*w/3, 0, 2*w/3, h);
                g2d.drawLine(0, h/3, w, h/3);
                g2d.drawLine(0, 2*h/3, w, 2*h/3);
            }
        };
        gamePanel.setLayout(new GridLayout(3, 3, 10, 10));
        gamePanel.setBackground(new Color(210, 180, 140)); // Tan color
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(new Color(210, 180, 140)); // Tan color
                buttons[i][j].setBorder(BorderFactory.createEmptyBorder());
                buttons[i][j].setOpaque(true);
                
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(e -> buttonClicked(row, col));
                
                gamePanel.add(buttons[i][j]);
            }
        }

        add(gamePanel, BorderLayout.CENTER);

        // Reset button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(210, 180, 140));
        JButton resetButton = new JButton("New Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(e -> resetGame());
        bottomPanel.add(resetButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setSize(500, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buttonClicked(int row, int col) {
        if (!buttons[row][col].getText().equals("")) {
            return;
        }

        if (xTurn) {
            buttons[row][col].setText("X");
            buttons[row][col].setForeground(xColor);
            statusLabel.setText(player2Name + "'s turn (O)");
        } else {
            buttons[row][col].setText("O");
            buttons[row][col].setForeground(oColor);
            statusLabel.setText(player1Name + "'s turn (X)");
        }

        moveCount++;

        if (checkWin()) {
            String winner = xTurn ? player1Name : player2Name;
            statusLabel.setText(winner + " wins!");
            disableButtons();
            JOptionPane.showMessageDialog(this, winner + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else if (moveCount == 9) {
            statusLabel.setText("It's a draw!");
            JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        xTurn = !xTurn;
    }

    private boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText())) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[0][i].getText().equals("") &&
                buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText())) {
                return true;
            }
        }

        // Check diagonals
        if (!buttons[0][0].getText().equals("") &&
            buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText())) {
            return true;
        }

        if (!buttons[0][2].getText().equals("") &&
            buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        xTurn = true;
        moveCount = 0;
        statusLabel.setText(player1Name + "'s turn (X)");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}