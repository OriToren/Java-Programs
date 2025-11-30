package Logic_Sara;

public class Atom extends Statement {
    int number;
public Atom(int number) {
    this.number=number;
    this.truth=true;

}
public Atom(int number,boolean truth){
    this.number=number;
    this.truth=truth;
}
public void setTrue(){
    this.truth=true;
}
public void setFalse() {
    this.truth = false;
}
    @Override
    public String toString() {
        return "p"+this.number;
    }
    public String truthLetter(){
    if (this.truth){
        return "T";
    }else {return "F";}
    }
    @Override
    public boolean equals(Object other){
    if (other instanceof Atom){
        if (((Atom) other).number == this.number){
            return true;
        }
    }
    return false;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(this.number);
    }

}
