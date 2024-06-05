import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WhackAMoleGame extends JFrame implements ActionListener {
    private final int GRID_SIZE = 3; // 3x3 网格
    private final JButton[][] buttons = new JButton[GRID_SIZE][GRID_SIZE];
    private final Random random = new Random();
    private int moleRow;
    private int moleCol;
    private int score = 0;
    private JLabel scoreLabel = new JLabel("score: 0");

    public WhackAMoleGame() {
        setTitle("Whack-a-Mole");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(this);
                gridPanel.add(buttons[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.NORTH);

        Timer timer = new Timer(1000, e -> moveMole());
        timer.start();

        setVisible(true);
    }

    private void moveMole() {
        if (moleRow >= 0 && moleCol >= 0) {
            buttons[moleRow][moleCol].setText("");
        }
        moleRow = random.nextInt(GRID_SIZE);
        moleCol = random.nextInt(GRID_SIZE);
        buttons[moleRow][moleCol].setText("M");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("M")) {
            score++;
            scoreLabel.setText("score: " + score);
            clickedButton.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WhackAMoleGame::new);
    }
}
