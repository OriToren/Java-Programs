package Logic_Sara;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogicFrame extends JFrame {
    CardLayout screensaver=new CardLayout();
    LogicFrame(){
        this.setSize(1200,900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        MainPanel mainpanel=new MainPanel();
        this.add(mainpanel);
        mainpanel.setLayout(screensaver);
        mainpanel.add(new KeyboredScreen(screensaver,mainpanel),"screen_1");
        this.setVisible(true);

    }
}
