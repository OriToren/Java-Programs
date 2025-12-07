package JavaBattleSim;

public class Poisoned extends StatusEffect{
    int dps;
    public Poisoned(int dps){
        super();
        this.dps=dps;
    }
    public Poisoned(int turns,int dps){
        super(turns);
        this.dps=dps;
    }
    @Override
    public void effect(FightEntity fighter) {
        if (turns>0){
            fighter.getDmg(dps);
            turns--;
        }
    }
    public String toString(){
        return "Poisoned";
    }

}
