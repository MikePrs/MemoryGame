package memorygame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.Random;

public class Play3x4 extends JFrame {

    int count = 0;
    int st = 1;
    String name;

    JFrame frame = new JFrame();
    static String files[] = {"src\\images\\1.png",
        "src\\images\\2.png",
        "src\\images\\3.png",
        "src\\images\\4.png",
        "src\\images\\5.png",
        "src\\images\\6.png"};
    static JButton buttons[];
    ImageIcon closedIcon;
    int numButtons;
    ImageIcon icons[];
    Timer myTimer;
    Timer chronos;

    int numClicks = 0;
    int oddClickIndex = 0;
    int currentIndex = 0;

    public Play3x4() {


        name = JOptionPane.showInputDialog(frame, "What's your name: \n");
        setTitle(name);


        setLayout(new GridLayout(3, 4));

        closedIcon = new ImageIcon("src\\images\\flipped.png");
        numButtons = files.length * 2;
        buttons = new JButton[numButtons];
        icons = new ImageIcon[numButtons];
        for (int i = 0, j = 0; i < files.length; i++) {
            icons[j] = new ImageIcon(files[i]);
            buttons[j] = new JButton("");
            buttons[j].addActionListener(new ImageButtonListener());
            buttons[j].setIcon(closedIcon);
            add(buttons[j++]);

            icons[j] = icons[j - 1];
            buttons[j] = new JButton("");
            buttons[j].addActionListener(new ImageButtonListener());
            buttons[j].setIcon(closedIcon);
            add(buttons[j++]);
        }


        Random gen = new Random();
        for (int i = 0; i < numButtons; i++) {
            int rand = gen.nextInt(numButtons);
            ImageIcon temp = icons[i];
            icons[i] = icons[rand];
            icons[rand] = temp;
        }


        pack();
        setVisible(true);

        myTimer = new Timer(1000, new TimerListener());
        chronos = new Timer(100000, new TimerListener2());

    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            buttons[currentIndex].setIcon(closedIcon);
            buttons[oddClickIndex].setIcon(closedIcon);
            myTimer.stop();
        }
    }

    private class TimerListener2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            chronos.stop();
            st = 0;
        }

    }

    private class ImageButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


            if (myTimer.isRunning()) {
                return;
            }

            numClicks++;

            chronos.start();

            System.out.println(numClicks);


            for (int i = 0; i < numButtons; i++) {
                if (e.getSource() == buttons[i]) {
                    buttons[i].setIcon(icons[i]);
                    currentIndex = i;
                }
            }


            if (numClicks % 2 == 0) {

                if (currentIndex == oddClickIndex) {
                    numClicks--;
                    return;
                }

                if (icons[currentIndex] != icons[oddClickIndex]) {

                    myTimer.start();
                } else {
                    count++;
                }
            } else {

                oddClickIndex = currentIndex;
            }

            String num = String.valueOf(numClicks);
            if (count == 6 && st == 1) {
                JOptionPane.showMessageDialog(frame, "You won " + name + "!" + "\n" + "moves:" + numClicks + "\n" + "time: less than a minute " );
                BufferedWriter bw = null;
                try {

                    bw = new BufferedWriter(new FileWriter("records.txt", true));
                    bw.write("WINNER WINNER CHICKEN DINNER ");
                    bw.write(" name: ");
                    bw.write(name);
                    bw.write(" moves: ");
                    bw.write(num);
                    bw.write(" time: less than a minute ");
                    bw.write(" level: 3x4 ");
                    bw.newLine();
                    bw.flush();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    if (bw != null) {
                        try {
                            bw.close();
                        } catch (IOException ioe2) {

                        }
                    }
                }
                if (JOptionPane.OK_OPTION == 1) {
                    frame.dispose();
                }
            }

            if (st == 0) {
                JOptionPane.showMessageDialog(frame, "You lost " + name + "!" + "\n" + "moves:" + numClicks + "\n" + "time's up ! ");
                BufferedWriter bw = null;
                try {

                    bw = new BufferedWriter(new FileWriter("records.txt", true));
                    bw.write(" LOSER ");
                    bw.write(" name: ");
                    bw.write(name);
                    bw.write(" time's up ");
                    bw.write(" level: 3x4 ");
                    bw.newLine();
                    bw.flush();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    if (bw != null) {
                        try {
                            bw.close();
                        } catch (IOException ioe2) {

                        }
                    }
                }
                if (JOptionPane.OK_OPTION == 1) {
                    frame.dispose();
                }
            }

        }

    }
}
