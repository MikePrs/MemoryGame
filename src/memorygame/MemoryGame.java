package memorygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MemoryGame extends JFrame {

    private String[] games = {"NewGame", "Restart", "Exit"};
    ImageIcon plex;

    public MemoryGame() {
        initcomponets();
    }
    int do1 = 0, do2 = 0, do3 = 0; //gia to restart

    private void initcomponets() {


        plex = new ImageIcon("src\\images\\font.png");
        iconLabel = new JLabel (plex);
        jPanel = new JPanel();// gia to allo miso
        jPanel.setLayout(new GridLayout(1, 2));

        buttonPanel = new JPanel();// gia ta koumpia
        buttonPanel.setLayout(new GridLayout(10, 1, 5, 10));

        game = new JComboBox(games);
        game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox game = (JComboBox) e.getSource();
                Object selected = game.getSelectedItem();
                if (selected == "Exit") {
                    System.exit(0);
                }
                if (selected == "Restart") {
                    if (do1 == 1) {
                        do1 = 0;
                        new Play3x4();
                    }
                    if (do2 == 1) {
                        do2 = 0;
                        new Play4x5();
                    }
                    if (do3 == 1) {
                        do3 = 0;
                        new Play6x6();
                    }
                }
            }
        });
        level_label = new JLabel("CHOOSE LEVEL");
        level1 = new JButton("3x4");

        level1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do1 = 1;
                new Play3x4();
            }
        });

        level2 = new JButton("4x5");
        level2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do2 = 1;
                new Play4x5();
            }
        });
        level3 = new JButton("6x6");
        level3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do3 = 1;
                new Play6x6();
            }
        });

        about = new JButton("ABOUT");
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DoAbout();
            }
        });
        other_label = new JLabel("Other");

        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("records.txt"))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        history = new JButton("HIstory Record");
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, contentBuilder);
            }
        });

        add(jPanel);

        buttonPanel.add(game);
        buttonPanel.add(level_label);
        buttonPanel.add(level1);
        buttonPanel.add(level2);
        buttonPanel.add(level3);
        buttonPanel.add(other_label);
        buttonPanel.add(history);
        buttonPanel.add(about);
        buttonPanel.setPreferredSize(new Dimension(500, 500));
        buttonPanel.setBackground(Color.BLACK);


        jPanel.add(iconLabel);
        jPanel.add(buttonPanel);
        jPanel.setBackground(Color.black);

        setSize(400, 400);
        setTitle("MEMORY GAME"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

    }

    public static void main(String[] args) {
        new MemoryGame().setVisible(true);

    }

    private JPanel jPanel, buttonPanel; // dhlwseis
    private JButton about, level1, level2, level3, history;
    private JComboBox game;
    private JLabel label, level_label, other_label, iconLabel;

}
