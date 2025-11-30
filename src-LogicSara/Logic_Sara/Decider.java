package Logic_Sara;

import java.util.ArrayList;

public class Decider {
    private static Boolean flipTruth(Boolean truth){
        if (truth==true){
            return false;
        }else{
            return true;
        }
    }
    public static boolean trueAtoms(Statement statement){
        if (statement instanceof Atom){
            return true;
        }
        if (statement instanceof Not){
            return flipTruth(trueAtoms(((Not) statement).Statement));
        }
        if (statement instanceof DoubleStatment){
            if (statement instanceof Or){
                return ((trueAtoms(((Or) statement).getAlpha_statment()) || trueAtoms(((Or) statement).getBeta_statment())));
            }
            if (statement instanceof And){
               return  ((trueAtoms(((And) statement).getAlpha_statment()) && trueAtoms(((And) statement).getBeta_statment())));
            }
            if (statement instanceof Iff){
                return (trueAtoms(((Iff) statement).getAlpha_statment()) == trueAtoms(((Iff) statement).getBeta_statment()));
            }
            if (statement instanceof IfSo){ //using the identity a->b is like not(a) v b that i found online.
                return (flipTruth(trueAtoms( ((IfSo) statement).getAlpha_statment())) || trueAtoms(((IfSo) statement).getBeta_statment()));
            }
        }
         return false; // this line cannot be reached unless for now
    }
    public static boolean falseAtoms(Statement statement) { //copied truth atoms but just changed the wording to falseAtoms.same with other versions.
        if (statement instanceof Atom) {
            return false;
        }
        if (statement instanceof Not) {
            return flipTruth(falseAtoms(((Not) statement).Statement));
        }
        if (statement instanceof DoubleStatment) {
            if (statement instanceof Or) {
                return (falseAtoms(((Or) statement).getAlpha_statment()) || falseAtoms(((Or) statement).getBeta_statment()));
            }
            if (statement instanceof And) {
                return (falseAtoms(((And) statement).getAlpha_statment()) && falseAtoms(((And) statement).getBeta_statment()));
            }
            if (statement instanceof Iff) {
                return (falseAtoms(((Iff) statement).getAlpha_statment()) == falseAtoms(((Iff) statement).getBeta_statment()));
            }
            if (statement instanceof IfSo) {
                return (flipTruth(falseAtoms(((IfSo) statement).getAlpha_statment())) || falseAtoms(((IfSo) statement).getBeta_statment()));
            }
        }

        return false;
    }
    public static boolean evenAtoms(Statement statement) {
        if (statement instanceof Atom) {
            if (((Atom) statement).number%2 == 0){
                return true;
            }else{return false;}
        }
        if (statement instanceof Not) {
            return flipTruth(evenAtoms(((Not) statement).Statement));
        }
        if (statement instanceof DoubleStatment) {
            if (statement instanceof Or) {
                return (evenAtoms(((Or) statement).getAlpha_statment()) || evenAtoms(((Or) statement).getBeta_statment()));
            }
            if (statement instanceof And) {
                return (evenAtoms(((And) statement).getAlpha_statment()) && evenAtoms(((And) statement).getBeta_statment()));
            }
            if (statement instanceof Iff) {
                return (evenAtoms(((Iff) statement).getAlpha_statment()) == evenAtoms(((Iff) statement).getBeta_statment()));
            }
            if (statement instanceof IfSo) {
                return (flipTruth(evenAtoms(((IfSo) statement).getAlpha_statment())) || evenAtoms(((IfSo) statement).getBeta_statment()));
            }
        }

        return false;
    }
    public static boolean oddAtoms(Statement statement) {
        if (statement instanceof Atom) {
            if (((Atom) statement).number%2 == 0){
                return false;
            }else{return true;}
        }
        if (statement instanceof Not) {
            return flipTruth(oddAtoms(((Not) statement).Statement));
        }
        if (statement instanceof DoubleStatment) {
            if (statement instanceof Or) {
                return (oddAtoms(((Or) statement).getAlpha_statment()) || oddAtoms(((Or) statement).getBeta_statment()));
            }
            if (statement instanceof And) {
                return (oddAtoms(((And) statement).getAlpha_statment()) && oddAtoms(((And) statement).getBeta_statment()));
            }
            if (statement instanceof Iff) {
                return (oddAtoms(((Iff) statement).getAlpha_statment()) == oddAtoms(((Iff) statement).getBeta_statment()));
            }
            if (statement instanceof IfSo) {
                return (flipTruth(oddAtoms(((IfSo) statement).getAlpha_statment())) || oddAtoms(((IfSo) statement).getBeta_statment()));
            }
        }

        return false;
    }
    public static boolean customAtoms(Statement statement,Atom[] Tatoms){
        if (statement instanceof Atom) {
            if (findAtom(Tatoms,(Atom) statement)){
                return true;
            }else{return false;}
        }
        if (statement instanceof Not) {
            return flipTruth(customAtoms(((Not) statement).Statement, Tatoms));
        }
        if (statement instanceof DoubleStatment) {
            if (statement instanceof Or) {
                return (customAtoms(((Or) statement).getAlpha_statment(), Tatoms) || customAtoms(((Or) statement).getBeta_statment(), Tatoms));
            }
            if (statement instanceof And) {
                return (customAtoms(((And) statement).getAlpha_statment(), Tatoms) && customAtoms(((And) statement).getBeta_statment(), Tatoms));
            }
            if (statement instanceof Iff) {
                return (customAtoms(((Iff) statement).getAlpha_statment(), Tatoms) == customAtoms(((Iff) statement).getBeta_statment(), Tatoms));
            }
            if (statement instanceof IfSo) {
                return (flipTruth(customAtoms(((IfSo) statement).getAlpha_statment(), Tatoms)) || customAtoms(((IfSo) statement).getBeta_statment(), Tatoms));
            }
        }
        return false;
    }
    public static boolean customAtoms(Statement statement,ArrayList<Atom> Tatoms){
        if (statement instanceof Atom) {
            if (findAtom(Tatoms,(Atom) statement)){
                return true;
            }else{return false;}
        }
        if (statement instanceof Not) {
            return flipTruth(customAtoms(((Not) statement).Statement, Tatoms));
        }
        if (statement instanceof DoubleStatment) {
            if (statement instanceof Or) {
                return (customAtoms(((Or) statement).getAlpha_statment(), Tatoms) || customAtoms(((Or) statement).getBeta_statment(), Tatoms));
            }
            if (statement instanceof And) {
                return (customAtoms(((And) statement).getAlpha_statment(), Tatoms) && customAtoms(((And) statement).getBeta_statment(), Tatoms));
            }
            if (statement instanceof Iff) {
                return (customAtoms(((Iff) statement).getAlpha_statment(), Tatoms) == customAtoms(((Iff) statement).getBeta_statment(), Tatoms));
            }
            if (statement instanceof IfSo) {
                return (flipTruth(customAtoms(((IfSo) statement).getAlpha_statment(), Tatoms)) || customAtoms(((IfSo) statement).getBeta_statment(), Tatoms));
            }
        }
        return false;
    }
    protected static boolean findAtom(Atom[] atoms,Atom target){
        for (Atom atom:atoms){
            if (target.equals(atom)){
                return true;
            }
        }
        return false;
    }
    protected static boolean findAtom(ArrayList<Atom> atoms, Atom target){
        for (Atom atom:atoms){
            if (target.equals(atom)){
                return true;
            }
        }
        return false;
    }


    
}
