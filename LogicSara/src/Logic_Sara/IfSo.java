package Logic_Sara;

public class IfSo extends DoubleStatment{
    public IfSo (Statement alpha,Statement beta){
        super(alpha,beta);
        if (this.alpha_statment.getTruth()==true && this.beta_statment.getTruth()==false) {
            this.truth = false;
        }else{this.truth=true;}
    }
    @Override
    public String toString(){
        return "("+alpha_statment.toString()+")->("+beta_statment.toString()+")";
    }
}
