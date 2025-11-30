package Logic_Sara;

import javax.swing.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    ArrayList<Statement> statements;
    ArrayList<JPanel> panels;
    MainPanel(){
        this.statements=new ArrayList<Statement>(1);
        this.panels=new ArrayList<JPanel>(1);
    }
    public ArrayList<Statement> getStatements(){
        return statements;
    }
    public void addStatmet(Statement s){
        this.statements.add(s);
    }
    public Statement getStatment(int index){
        return this.statements.get(index);
    }
    public void addPanel(JPanel panel){
        panels.add(panel);
    }
    public JPanel getPanel(int index){
        return this.panels.get(index);
    }
}
