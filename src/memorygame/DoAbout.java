
package memorygame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DoAbout extends JFrame {
    JFrame frame =new JFrame();
    public DoAbout(){
     JOptionPane.showMessageDialog
                            (frame, "MEMORY GAME\n"
                                    + "Made by\n"+"Mike Paraskevopoulos\n"+
                                    "& \n" + "George Freris \n"+"version 1.0", "About", 
                                    JOptionPane.INFORMATION_MESSAGE);
    }
}