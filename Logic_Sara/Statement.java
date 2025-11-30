package Logic_Sara;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Statement {
    boolean truth;

    private void setTrue() {
        this.truth = true;
    }

    private void setFalse() {
        this.truth = false;
    }

    public Boolean getTruth() {
        return this.truth;
    }

    public Boolean isAlwaystrue() {
        HashSet<Atom> uniqueatoms = this.getUniqueAtoms();
        int cols = uniqueatoms.size();
        ArrayList<Atom> uniqueatomsalso=new ArrayList<>();
        uniqueatomsalso.addAll(uniqueatoms);
        for (int i = 0; i < power(2, cols); i++) {
            ArrayList<Atom> trueatoms = new ArrayList<>();
            String byte_i = Integer.toBinaryString(i); //took this from my truthtable func,slightly modified to fit this purpose.
            int i_len = byte_i.length();
            for (int j = 0; j < cols - i_len; j++) {
                byte_i = "0" + byte_i;
            }
            for (int j = 0; j < cols; j++) {
                if (byte_i.codePointAt(j) == 48) {
                    trueatoms.add(uniqueatomsalso.get(j));
                }
            }
            if (!(Decider.customAtoms(this,trueatoms))){
                return false;
            }


        }
        return true;
    }

    public Boolean isAlwaysfalse() {
        HashSet<Atom> uniqueatoms = this.getUniqueAtoms();
        int cols = uniqueatoms.size();
        ArrayList<Atom> uniqueatomsalso=new ArrayList<>();
        uniqueatomsalso.addAll(uniqueatoms);
        for (int i = 0; i < power(2, cols); i++) {
            ArrayList<Atom> trueatoms = new ArrayList<>();
            String byte_i = Integer.toBinaryString(i);
            int i_len = byte_i.length();
            for (int j = 0; j < cols - i_len; j++) {
                byte_i = "0" + byte_i;
            }
            for (int j = 0; j < cols; j++) {
                if (byte_i.codePointAt(j) == 48) {
                    trueatoms.add(uniqueatomsalso.get(j));
                }
            }
            if ((Decider.customAtoms(this,trueatoms))){
                return false;
            }


        }
        return true; // the exact same as isalwaystrue just checks if it is true and if it sees that its true once it returns false;

    }
    public int atomCounter(){
        if (this instanceof Atom){
            return 1;
        }
        if (this instanceof Not){
            return  ((Not) this).Statement.atomCounter();
        }
            return ((DoubleStatment) this).getAlpha_statment().atomCounter() + ((DoubleStatment) this).getBeta_statment().atomCounter();

    }
    public int uniqueAtomcounter() {
        HashSet<Atom> diff_atoms = new HashSet<>();
        return uniqueAtomcounter_private(diff_atoms);


        }
    private int uniqueAtomcounter_private(HashSet<Atom> atomlisty) {
        if (this instanceof Atom){
            if (atomlisty.add((Atom) this)){
                return 1;
            }else {return 0;}
        }
        if (this instanceof Not) {
            return ((Not) this).Statement.uniqueAtomcounter_private(atomlisty);
        }
        if (this instanceof DoubleStatment) {
            return ((DoubleStatment) this).getAlpha_statment().uniqueAtomcounter_private(atomlisty) + ((DoubleStatment) this).getBeta_statment().uniqueAtomcounter_private(atomlisty);
        }
        return 0;
    }
    public HashSet<Atom> getUniqueAtoms(){
        HashSet<Atom> diff_atoms = new HashSet<>();
        return getUniqueAtoms_private(diff_atoms);
    }
    private HashSet<Atom> getUniqueAtoms_private(HashSet<Atom> diff_atoms){
        if (this instanceof Atom){
            diff_atoms.add((Atom) this);
        }
        if (this instanceof Not){
            ((Not) this).Statement.getUniqueAtoms_private(diff_atoms);
        }
        if (this instanceof DoubleStatment){
            ((DoubleStatment) this).alpha_statment.getUniqueAtoms_private(diff_atoms);
            ((DoubleStatment) this).beta_statment.getUniqueAtoms_private(diff_atoms);
        }
        return diff_atoms;
    }


    @Override
    public boolean equals(Object other) {
        if (other instanceof Statement) {
            Iff comparison = new Iff(this, (Statement) other);
            if (comparison.isAlwaystrue()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    public String truthTable() {
        HashSet<Atom> atoms = this.getUniqueAtoms();
        ArrayList<Atom> atomlist = new ArrayList<>();
        atomlist.addAll(atoms);
        String truthtable = "";
        int cols = atoms.size();
        for (Atom atom : atoms) {
            truthtable += " " + atom + " |";
        }
        truthtable += " " + this.toString();
        //acording to random checks i did 5 of these ----- is enough to fill an atom space
        String seperator = "-----";
        truthtable += "\n";
        for (int i = 0; i < cols; i++) {
            truthtable += seperator;
        }
        // i have no idea the size of this so il act accordingly
        int statmentlen = this.toString().length();
        for (int i = 0; i < statmentlen; i++) {
            truthtable += '-';
        }
        truthtable += '\n';
        // this im after doing some examples on paper. each p(i) has 2^(cols-i) alternating T and F(this assuming p1 is the first and pi is the last).
        for (int i = 0; i < power(2, cols); i++) {
            String tempstring = "";
            ArrayList<Atom> trueatoms = new ArrayList<>();
            String byte_i = Integer.toBinaryString(i);// had to look up how to do this since using binary seems like the only solution that wont take me 5 hrs like the other ones i tried
            //now i need to zero extend
            int i_len = byte_i.length();
            for (int j = 0; j < cols - i_len; j++) {
                byte_i = "0" + byte_i;
            }
            // ok now by asci 49 for 1 value and 48 for 0 im a geniusss haha
            for (int j = 0; j < cols; j++) {
                if (byte_i.codePointAt(j) == 48) {
                    tempstring += " T  |";
                    trueatoms.add(atomlist.get(j));
                }
                if (byte_i.codePointAt(j) == 49) {
                    tempstring += " F  |";

                }
            }
            if (Decider.customAtoms(this, trueatoms)) {
                tempstring += " T";
            } else{
                tempstring += " F";
            }
            truthtable += tempstring;
            truthtable += "\n"; ////////// i did it aaaaaaaaaaaa
        }



            return truthtable;
        }


    private  static int power(int num,int power){ //cant believe java doesnt have this naturally :(
        if (power==0){
            return 1;
        }
        int originalnum=num;
        for (int i=1;i<power;i++){
            num*=originalnum;
        }
        return num; //note this only works for integer powers greater or equal to zero.
    }


}



