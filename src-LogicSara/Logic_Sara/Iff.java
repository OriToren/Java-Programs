package Logic_Sara;

public class Iff extends DoubleStatment{
    public Iff(Statement alpha,Statement beta){
        super(alpha,beta);
        if (alpha_statment.getTruth()==beta_statment.getTruth()){
            this.truth=true;
        }else{this.truth=false;}
    }
    @Override
    public String toString(){
       return "("+alpha_statment.toString()+")<->("+beta_statment.toString()+")";
    }
}
