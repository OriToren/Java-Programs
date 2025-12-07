package JavaBattleSim;

public abstract class Equipment {
    public abstract boolean effect(Player entity);
    public abstract boolean isThereEffect();
    public String printEffect(){
        if (isThereEffect()){
            return this.toString()+" effect ";
        }
        return null;
    }
}
