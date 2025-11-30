package Logic_Sara;

import static Logic_Sara.Shortener.*;

public class LogicParser {
    /* lets imagine a Statment with all operators.
        ((p1)v(p2)^(p1->p2)<->(~(p1)))
        we would want atoms to activate first. then not. the v,^ . then ->, then <->.
        by the end of the day its decided by () but its still good to have a natural order.
        lets try to do this!
     */
    private String text;
    private int pos=-1;
    private int ch;
    public LogicParser(String text){
        this.text=text;
        nextChar();
    }
    private void nextChar(){
        if (++pos<this.text.length()){
            this.ch=this.text.charAt(pos);
        }else{ch=-1;}
    }
    private boolean devour(int chr){
        while (this.ch==' '){nextChar();}
        if (this.ch==chr){
            nextChar();
            return true;
        }return false; //up to here copied from parser_practice;


    }
    public Statement parse(){
        if (this.text.isEmpty()){
            throw new IllegalArgumentException("field is empty");
        }
        Statement x=parseIff();
        if (ch != -1 ){
            throw new IllegalArgumentException("trash at the end of Statment. clean it up");
        }
        return x;
    }
    private Statement parseIff(){
        Statement x=parseIfso();
        while (true){
            if (devour('<')){
                if (devour('-') && devour('>')) {
                    Statement right = parseIfso();
                    x = iff(x, right);
                }else{throw new IllegalArgumentException("Iff must be written as <->");}
            }else{return x;}
        }
    }

    private Statement parseIfso() {
    Statement x=parseOr();
    while (true){
        if (devour('-')){
            if (devour('>')) {
                Statement right = parseOr();
                x = ifso(x, right);
            }else{throw new IllegalArgumentException("Ifso must be written as ->");}
        }else{return x;}
    }
    }
    private Statement parseOr() {
    Statement x=parseAnd();
    while(true){
        if (devour('v')){
            Statement right=parseAnd();
            x=or(x,right);
        }else if (devour('o')){
            if (devour('r')){
                Statement right=parseAnd();
                x=or(x,right);
            }else{throw new IllegalArgumentException("o alone isn't a valid char\n (you can write or tho) \n at pos -> "+pos);}
        }else{return x;}

    }
    }
    private Statement parseAnd() {
        Statement x = parseNot();
        while (true) {
            if (devour('∧')) {
                Statement right = parseNot();
                x = and(x, right);
            } else if (devour('a')){
                if (devour('n') && devour('d')){
                    Statement right=parseNot();
                    x=and(x,right);
                }else{throw new IllegalArgumentException("a or an alone isn't valid syntax \n (you can write and tho) \n at pos -> "+pos);}
            }else{return x;}
        }
    }
    private Statement parseNot(){
        while(true){ //here we dont check for x since for example in + x is suppose to represnt the left and right represnts the right so x+right. here not is depent only on what on its right so we dont need to check for any x.i think this is an important lesson for me since i didnt know this before making the Not parser.
            if (devour('~')){
                return not(parseNot());
            }else{return parseAtom();}
        }
    }

    private Statement parseAtom() {
        if (devour('(')) {
            Statement x = parseIff();
            devour(')');
            return x;
        }
        if (devour('p')) {
            if (this.ch >= '0' && this.ch <= '9') {
                int start = pos;
                nextChar();
                while (this.ch >= '0' && this.ch <= '9') {
                    nextChar();
                }
                String numstring = text.substring(start, pos);
                int realnum = Integer.parseInt(numstring);
                return p(realnum);
            } else {
                throw new IllegalArgumentException("after Atom p, there must be a number indicating \nits difference or equality to other Atoms");
            }
        }
             if (ch>0){
            throw new IllegalArgumentException("Syntax error!!! \n the error-> " + (char) ch + " is an invalid char \n at position-> " + pos);
        }else{throw new IllegalArgumentException("statement incomplete");}


}
public static boolean isValidStatment(String text){
        try{
            logicParse(text);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }

    }
    public static String isValidStatment_print(String text){
        try{
            logicParse(text);
            return "";
        }catch (IllegalArgumentException e){
            String exception=e.toString().replaceAll("java.lang.IllegalArgumentException:","");
            return exception;
        }
    }

    }
