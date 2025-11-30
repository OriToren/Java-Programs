package Logic_Sara;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static Logic_Sara.Shortener.logicParse;

class KeyboredScreen extends JPanel {
    CardLayout screensaver;
    MainPanel mainpanel;
    Boolean does_infoscreen_exist=false;
    Boolean does_screen2_exist=false;
    KeyboredScreen(CardLayout screensaver, MainPanel mainpanel)  {
        this.screensaver = screensaver;
        this.mainpanel = mainpanel;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200, 900));
        JPanel northpanel = new JPanel();
        northpanel.setLayout(new BorderLayout());
        JTextField statmentfield = new JTextField();
        statmentfield.setPreferredSize(new Dimension(50, 100));
        statmentfield.setFont(new Font("Cambria", Font.BOLD, 60));
        statmentfield.setText("type statement here...");
        northpanel.add(statmentfield, BorderLayout.NORTH);
        JTextArea execptionarea = new JTextArea();
        execptionarea.setPreferredSize(new Dimension(75, 200));
        execptionarea.setFont(new Font("Cambria", Font.BOLD, 40));
        execptionarea.setText("here we will find out if you have any mistakes \n so type away!!!");
        execptionarea.setEditable(false);
        northpanel.add(execptionarea, BorderLayout.CENTER);
        statmentfield.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String currenttext = statmentfield.getText();
                String execptiontext = LogicParser.isValidStatment_print(currenttext);
                execptionarea.setText(execptiontext);

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String currenttext = statmentfield.getText();
                String execptiontext = LogicParser.isValidStatment_print(currenttext);
                execptionarea.setText(execptiontext);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        JButton enter = new JButton("enter statement!");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LogicParser.isValidStatment(statmentfield.getText())) {
                    does_screen2_exist=true;
                    FunctionScreen funcscreen=new FunctionScreen(screensaver, mainpanel, logicParse(statmentfield.getText()));
                    mainpanel.addPanel(funcscreen);
                    mainpanel.add(funcscreen,"screen_2");
                    screensaver.show(mainpanel, "screen_2");
                } else {
                    execptionarea.setText("cannot enter an invalid statment");
                }
            }

        });
        enter.setPreferredSize(new Dimension(239, 100)); //if youre asking why this number is so random i adjusted it to fit the buttons below.
        enter.setFont(new Font("Cambria", Font.BOLD, 25));
        northpanel.add(enter, BorderLayout.EAST);
        this.add(northpanel, BorderLayout.NORTH);
        String[] keywords={"p","~","v","and","->","<->","(",")","1","2","3","4","5","6","7","8","9"};
        JPanel buttonpanel=new JPanel();
        buttonpanel.setLayout(new GridLayout(4,6));
        for (String keyword:keywords){
            JButton typebutton=new JButton(keyword);
            typebutton.setFont(new Font("Cambria",Font.BOLD,35));
            typebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    statmentfield.setText(statmentfield.getText()+keyword);
                }
            });
            buttonpanel.add(typebutton);
        }
        JButton delete=new JButton("Del");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statmentfield.getText().isEmpty() ==false) {
                    statmentfield.setText(statmentfield.getText().substring(0, statmentfield.getText().length() - 1));
                }
            }
        });
        delete.setFont(new Font("Cambria",Font.BOLD,40));
        buttonpanel.add(delete);
        JButton clear=new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statmentfield.setText("");
            }
        });
        clear.setFont(new Font("Cambria",Font.BOLD,40));
        buttonpanel.add(clear);
        this.add(buttonpanel,BorderLayout.CENTER);
        JButton infobutton=new JButton("App Info");
        infobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (does_infoscreen_exist == false){
                    does_screen2_exist=true;
                    mainpanel.add(new InfoScreen(screensaver, mainpanel, statmentfield.getText(),does_screen2_exist), "info_screen");
                    does_infoscreen_exist=true;
                }
                screensaver.show(mainpanel,"info_screen");
            }
        });

        infobutton.setFont((new Font("Cambria",Font.BOLD,40)));
        buttonpanel.add(infobutton);
        /*
        ImageIcon cornerimage = new ImageIcon(getClass().getResource("/Logic_Sara/starry.jpg")); // i wanted fucking starrynight in the corner so i now learned how to add these
        Image image=cornerimage.getImage();
        Image new_image=image.getScaledInstance(255,155, Image.SCALE_SMOOTH); //this supposdly smoothes the picture
        ImageIcon final_image=new ImageIcon(new_image);
        JLabel cornercolor_screen1=new JLabel(final_image);
        buttonpanel.add(cornercolor_screen1);
        maybe il use this later
         */
    }
}
