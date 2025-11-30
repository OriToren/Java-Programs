package Logic_Sara;

public class Not extends Statement {
    Statement Statement;
    public Not(Statement statement){
        this.Statement=statement;
        this.truth=flipTruth(Statement.truth);

}
@Override
    public String toString(){
        return "~("+this.Statement.toString() + ")";
}
    private Boolean flipTruth(Boolean truth){
        if (truth==true){
            return false;
        }else{
            return true;
        }
    }
}

