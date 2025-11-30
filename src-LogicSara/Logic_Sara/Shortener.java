package Logic_Sara;

import java.util.ArrayList;

public class Shortener {
    public static Atom p(int num) {
        return new Atom(num);
    }
    public static Atom p(int num, Boolean truth) {
        return new Atom(num, truth);
    }

    public static Not not(Statement statement) {
        return new Not(statement);
    }

    public static Or or(Statement alpha, Statement beta) {
        return new Or(alpha, beta);
    }

    public static And and(Statement alpha, Statement beta) {
        return new And(alpha, beta);
    }

    public static Iff iff(Statement alpha, Statement beta) {
        return new Iff(alpha, beta);
    }

    public static IfSo ifso(Statement alpha, Statement beta) {
        return new IfSo(alpha, beta);
    }

    public static Not xor(Statement alpha, Statement beta) {
        return not(iff(alpha, beta));
    }

    public static Not nor(Statement alpha, Statement beta) {
        return not(or(alpha, beta));
    }

    public static Not nand(Statement alpha, Statement beta) {
        return not(and(alpha, beta));
    }

    public static Statement logicParse(String text) { //quick parsing;
            LogicParser parsi = new LogicParser(text);
            return parsi.parse();
    }
    private static ArrayList<Atom> atomParse(String text,Statement statement){
        CustomScreenParser customScreenParser=new CustomScreenParser(text,statement);
        return customScreenParser.parse();
    }
    public static LogicFrame enterProgram(){
        return new LogicFrame();
    }



}
