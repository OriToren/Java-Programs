package JavaBattleSim;

public class Incapacitated extends StatusEffect{
    public Incapacitated(){
        super();
    }
    public Incapacitated(int turns){
        super(turns);
    }
    @Override
    public void effect(FightEntity fighter) {
        turns--; //this makes the player unable to fight. tho isn't referenced here in BattleOperationsList this will be referenced.
    }
    public String toString(){
        return "Incapacitated";
    }
}
