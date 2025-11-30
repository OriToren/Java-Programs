package Logic_Sara;

import java.util.ArrayList;
import Logic_Sara.LogicParser.*;
import Logic_Sara.Statement.*;
public class CustomScreenParser {
    private String text;
    private Statement statement;
    private ArrayList<Atom> atomlist;
    private int ch;
    private int pos;
    CustomScreenParser(String text,Statement statement){
        this.text=text;
        this.statement=statement;
        this.atomlist=new ArrayList<Atom>();
        pos=-1;
        nextChar();
    }
    private void nextChar(){
        if(++pos<text.length()){
            ch=text.charAt(pos);
        }else{ch=-1;}
    }
    private boolean devour(char other){
        while(ch==' '){nextChar();}
        if (ch==other){
            nextChar();
            return true;
        }else{return false;}
    }
    public ArrayList<Atom> parse(){
        if (text.isEmpty()){
            throw new IllegalArgumentException("you have to write something man...");
        }
        parsetag();
        if (ch != -1){
            throw new IllegalArgumentException("write only atoms please and write , between every atom");
        }
        return atomlist;
    }
    private ArrayList<Atom> parsetag(){
        parseAtom();
        while (true) {
        if (devour(',')) {
             parseAtom();
        }else{return atomlist;}
    }
    }
    private void parseAtom(){
    if (devour('p')){
        int temppos=pos-1;
        if (ch>='0' && ch<='9'){
            nextChar();
        }else{throw new IllegalArgumentException("after Atom p, there must be a number indicating \nits difference or equality to other Atoms");}
        while(ch>='0' && ch<='9'){
            nextChar();
        }
        Statement unkownp=new LogicParser(text.substring(temppos,pos)).parse(); //will always be an atomn
        if (statement.getUniqueAtoms().add((Atom)unkownp)){
            throw new IllegalArgumentException("this Atom isnt in the original Statment: \n"+text.substring(temppos,pos));
        }else {atomlist.add((Atom)unkownp);}
    }else{
    throw new IllegalArgumentException("Syntax error!!! \n the error-> " + (char) ch + " is an invalid char \n at position-> " + pos);}

        }
}
