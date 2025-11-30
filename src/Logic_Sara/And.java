package Logic_Sara;

public class And extends  DoubleStatment{
    public And (Statement alpha,Statement beta){
        super(alpha,beta);
        if (this.alpha_statment.truth==true && this.beta_statment.truth==true){
            this.truth=true;
        }else{this.truth=false;}
    }
    @Override
    public String toString(){
        return "("+alpha_statment.toString()+")∧("+beta_statment.toString()+")";
    }
}
