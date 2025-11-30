package Logic_Sara;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Logic_Sara.Shortener.logicParse;

public class FunctionScreen extends JPanel {
    CardLayout screensaver;
    MainPanel mainpanel;
    Statement statment;
    JTextArea msgarea;
    FunctionScreen(CardLayout screensaver,MainPanel mainpanel,Statement statement){
        this.screensaver=screensaver;
        this.mainpanel=mainpanel;
        String statment=statement.toString();
        String noandstatment=statment.replaceAll("and","∧");
        String noorstatment=noandstatment.replaceAll("or","v");
        this.statment=logicParse(noorstatment);
        this.setPreferredSize(new Dimension(1200, 900));
        this.setLayout(new BorderLayout());
        JPanel northpanel=new JPanel();
        northpanel.setLayout(new BorderLayout());
        JTextArea statmentlabel=new JTextArea("Current Statment:\n"+statment.toString());
        statmentlabel.setPreferredSize(new Dimension(500,100));
        statmentlabel.setEditable(false);
        statmentlabel.setFont(new Font("Cambria",Font.BOLD,40));
        JScrollPane fillerforstatmentlabel=new JScrollPane(statmentlabel);
        fillerforstatmentlabel.createVerticalScrollBar();
        northpanel.add(fillerforstatmentlabel,BorderLayout.CENTER);
        JButton returnbutton=new JButton("return");
        returnbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screensaver.show(mainpanel,"screen_1");
            }
        });
        returnbutton.setPreferredSize(new Dimension(200,100));
        returnbutton.setFont(new Font("Cambria",Font.BOLD,40));
        northpanel.add(returnbutton,BorderLayout.WEST);
        JButton infobutton=new JButton("App Info");
        infobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screensaver.show(mainpanel,"info_screen");
            }
        });

        infobutton.setFont((new Font("Cambria",Font.BOLD,40)));
        infobutton.setPreferredSize(new Dimension(200,100));
        northpanel.add(infobutton,BorderLayout.EAST);
        JTextArea msgsarea=new JTextArea();
        JScrollPane msgareascroller=new JScrollPane(msgsarea);
        msgareascroller.setPreferredSize(new Dimension(1000,250));
        msgsarea.setEditable(false);
        this.msgarea=msgsarea;
        msgsarea.setFont(new Font("Cambria",Font.PLAIN,25));
        JPanel northsouth=new JPanel();
        northsouth.setLayout(new BorderLayout());
        northsouth.add(msgareascroller,BorderLayout.CENTER);
        JButton clear=new JButton("Clear");
        clear.setFont((new Font("Cambria",Font.BOLD,25)));
        clear.setPreferredSize(new Dimension(200,125));
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                msgsarea.setText("");
            }
        });
        northsouth.add(clear,BorderLayout.EAST);
        JButton deleteline=new JButton("Del");
        deleteline.setFont((new Font("Cambria",Font.BOLD,25)));
        deleteline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(msgsarea.getText().isEmpty())){
                    msgsarea.setText(msgsarea.getText().substring(0,msgsarea.getText().length()-1));
                }
            }
        });
        deleteline.setPreferredSize(new Dimension(200,125));
        JPanel northsoutheast=new JPanel();
        northsoutheast.setLayout(new GridLayout(2,1));
        northsoutheast.add(clear);
        northsoutheast.add(deleteline);
        northsouth.add(northsoutheast,BorderLayout.EAST);
        northpanel.add(northsouth,BorderLayout.SOUTH);
        this.add(northpanel,BorderLayout.NORTH);
        msgsarea.setEditable(false);
        JPanel buttonpanel=new JPanel();
        buttonpanel.setLayout(new GridLayout(3,3));
        JButton isalwaystrue=new JButton("is tautology?");
        isalwaystrue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (statement.isAlwaystrue()) {
                   msgsarea.setText(msgsarea.getText() + statement.toString()+" is a tautology(True)\n");
               }else{msgsarea.setText(msgsarea.getText() + statement.toString()+" is not a tautology(False)\n");}
            }
        });
        isalwaystrue.setFont(new Font("Cambria",Font.BOLD,30));
        buttonpanel.add(isalwaystrue);
        JButton isalwaysfalse=new JButton("is contradiction?");
        isalwaysfalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statement.isAlwaysfalse()) {
                    msgsarea.setText(msgsarea.getText() + statement.toString()+" is a contradiction(True)\n");
                }else{msgsarea.setText(msgsarea.getText() + statement.toString()+" is not a contradiction(False)\n");}
            }
        });
        isalwaysfalse.setFont(new Font("Cambria",Font.BOLD,30));
        buttonpanel.add(isalwaysfalse);
        JButton allfalse=new JButton("false atoms");
        allfalse.setFont(new Font("Cambria",Font.BOLD,30));
        allfalse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Decider.falseAtoms(statement)){
                    msgsarea.setText(msgsarea.getText()+statement.toString()+"when all Atoms are false the statment is True\n");
                }else {msgsarea.setText(msgsarea.getText()+statement.toString()+"when all Atoms are false the statment is False\n");}
            }
        });
        JButton alltrue=new JButton("true atoms");
        alltrue.setFont(new Font("Cambria",Font.BOLD,30));
        alltrue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Decider.trueAtoms(statement)){
                    msgsarea.setText(msgsarea.getText()+statement.toString()+"when all Atoms are true the statment is True\n");
                }else {msgsarea.setText(msgsarea.getText()+statement.toString()+"when all Atoms are true the statment is False\n");}
            }
        });
        buttonpanel.add(alltrue);
        buttonpanel.add(allfalse);
        this.add(buttonpanel);
        JButton evenatoms=new JButton("even atoms");
        evenatoms.setFont(new Font("Cambria",Font.BOLD,30));
        evenatoms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Decider.evenAtoms(statement)){
                    msgsarea.setText(msgsarea.getText()+statement.toString()+"when all even Atoms are true the statment is True\n");
                }else {msgsarea.setText(msgsarea.getText()+statement.toString()+"when all even Atoms are true the statment is False\n");}

            }
        });
        buttonpanel.add(evenatoms);
        JButton oddatoms=new JButton("odd atoms");
        oddatoms.setFont(new Font("Cambria",Font.BOLD,30));
        oddatoms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Decider.oddAtoms(statement)){
                    msgsarea.setText(msgsarea.getText()+statement.toString()+"when all odd Atoms are true the statment is True\n");
                }else {msgsarea.setText(msgsarea.getText()+statement.toString()+"when all odd Atoms are true the statment is False\n");}
            }
        });
        buttonpanel.add(oddatoms);
        JButton customatoms=new JButton("custom atoms");
        customatoms.setFont(new Font("Cambria",Font.BOLD,30));
        customatoms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(this, "enter atoms to set to truth"); //apparently this is some sort of a skip instead of creating a whole new panel. was cool learning this
                if (!(input == null)) {
                    try {
                        CustomScreenParser parser = new CustomScreenParser(input, statement);
                        ArrayList<Atom> trueAtoms = parser.parse();
                        boolean result = Decider.customAtoms(statement, trueAtoms);
                        msgsarea.setText(msgsarea.getText() + statement.toString() + "custom Atoms check: when these Atoms " + trueAtoms.toString() + " are true the statment is " + result + "\n");

                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Syntax Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });

        buttonpanel.add(customatoms);
        
        JButton compare=new JButton("Compare to");
        compare.setFont(new Font("Cambria",Font.BOLD,30));
        compare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainpanel.add(new CompareScreen(screensaver,mainpanel),"compare_screen");
                screensaver.show(mainpanel,"compare_screen");
            }
        });
        buttonpanel.add(compare);
        JButton truthtable=new JButton("Truth Table");
        truthtable.setFont(new Font("Cambria",Font.BOLD,30));
        truthtable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                msgsarea.setText(msgsarea.getText()+"Truth Table of Statment:"+statment+"\n"+statement.truthTable()+"\n");
            }
        });
        buttonpanel.add(truthtable);
    }
    protected void compareStatments(Statement other){
        mainpanel.statements.removeFirst();
        if (this.statment.equals(other)) {
            msgarea.setText(msgarea.getText() + "the Statment:  " + this.statment + "\nand the Statment:  " + other + "\nare Equal\n");
        }else{ msgarea.setText(msgarea.getText() + "the Statment:  " + this.statment + "\nand the Statment:  " + other + "\nare not Equal\n");}
    }

}
