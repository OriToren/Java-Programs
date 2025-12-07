package JavaBattleSim;

public interface Fightable{
    public abstract void fight(FightEntity opp); //already in fightentity.
    public abstract void block(); //already in fight entity
    public abstract void addItem(Item item); //in Monster,Player
    public abstract void useItem(Item item); // in Monster,Player
    public abstract void run(Battle battle); // already in fightentity //
    public abstract void resetTurn(); //already in Fightentity
}
