package View;

import Models.Box;
import Models.Game;
import Models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;


public class MainGUI extends JFrame {
    private JPanel mainPanel = new JPanel(), dashboard = new JPanel();

    final Image defaultIconScaled = scaleImage(new ImageIcon("src/assets/?.png").getImage());
    final JLabel lbName = new JLabel(), lbMissing = new JLabel(), lbLevel = new JLabel(), lbScore = new JLabel();
    final Box[] markedBoxes = new Box[2];
    final JLabel[] markedLabels = new JLabel[2];
    final Game game;
    final Player player;

    private String name = "SN";
    private int missing;

    public MainGUI() throws IOException {
        super("Memory Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.player = new Player(this.name);
        this.game = new Game(this.player);
        setPlayerName();
        fillDashboard();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.pack();
    }

    private void fillDashboard() {
        Box[][] boxes = game.getDashboard().getBoxes();
        int row = game.getDashboard().getRowsSize();
        int column = game.getDashboard().getColumnsSize();
        missing = game.getDashboard().getMissing();
        lbMissing.setText("Missing couples: " + missing);
        lbLevel.setText("level: " + game.getLevel());
        lbScore.setText("Score: " + game.getPlayer().getScore());
        dashboard.setLayout(new GridLayout(row, column));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                JLabel temp = new JLabel();
                final Box box = boxes[i][j];
                temp.setIcon(new ImageIcon(defaultIconScaled));
                temp.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        Image originalIcon = scaleImage(new ImageIcon(box.getImage()).getImage());
                        temp.setIcon(new ImageIcon(originalIcon));
                        if (!box.isPaired()) {
                            if (markedBoxes[0] == null) {
                                markedBoxes[0] = box;
                                markedLabels[0] = temp;
                            } else if (markedBoxes[1] == null) {
                                markedBoxes[1] = box;
                                markedLabels[1] = temp;
                            }
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                        if (markedBoxes[1] != null) {
                            try {
                                validateBoxes();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                    }
                });
                dashboard.add(temp);
            }
        }
        mainPanel.add(lbMissing);
        mainPanel.add(lbLevel);
        mainPanel.add(lbScore);
        mainPanel.add(dashboard, BorderLayout.CENTER);
    }

    private void validateBoxes() throws IOException {
        if (game.validateMove(markedBoxes[0], markedBoxes[1])) {
            missing = game.getDashboard().getMissing();
            lbMissing.setText("Missing couples: " + missing);
            lbScore.setText("Score: " + game.getPlayer().getScore());
        } else {
            markedLabels[0].setIcon(new ImageIcon(defaultIconScaled));
            markedLabels[1].setIcon(new ImageIcon(defaultIconScaled));
        }
        markedLabels[0] = null;
        markedLabels[1] = null;
        markedBoxes[0] = null;
        markedBoxes[1] = null;
        isLevelCompleted();
    }


    private void advanceNextLevel() throws IOException {
        game.advanceNextLevel();
        dashboard.removeAll();
        mainPanel.remove(dashboard);
        fillDashboard();
    }

    private void isLevelCompleted() throws IOException {
        if (missing == 0) {
            int response = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to advance to the next one?",
                    "Level completed.",
                    JOptionPane.YES_NO_OPTION);
            if (response == 0) advanceNextLevel();
            if (response == 1 || response == -1) System.exit(0);
        }
    }

    private void setPlayerName() {
        String playerName = (String) JOptionPane.showInputDialog(null, "Please enter your name",
                "Welcome",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);
        if (playerName == null) System.exit(0);
        if (!playerName.equals("")) this.name = playerName;
        this.lbName.setText("Player: " + this.name);
        this.mainPanel.add(this.lbName);
    }

    private Image scaleImage(Image image) {
        int width = 100, height = 100;
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
