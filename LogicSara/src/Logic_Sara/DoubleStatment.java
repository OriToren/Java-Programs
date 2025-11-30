package Logic_Sara;

public abstract class DoubleStatment extends Statement{
    Statement alpha_statment;
    Statement beta_statment;
public DoubleStatment(Statement alpha,Statement beta){
    this.alpha_statment=alpha;
    this.beta_statment=beta;
}
public Statement getAlpha_statment(){
    return this.alpha_statment;
}
public Statement getBeta_statment(){
    return this.beta_statment;
}
}
