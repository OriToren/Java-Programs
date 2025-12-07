package JavaBattleSim;

public class Bleeding extends StatusEffect {
    public Bleeding(){
        super();
    }
    public Bleeding(int turns){
        super(turns);
    }
    @Override
    public void effect(FightEntity fighter) {
            fighter.getDmg((int) (fighter.getMaxhp() * 0.1));
            turns--;

    }
    public String toString(){
        return "Bleeding";
    }
}
