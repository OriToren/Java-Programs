package JavaBattleSim;

public interface BattleOperations {
    public abstract void startBattle();
    public abstract void endBattle();
    public abstract boolean didEnd();
    public abstract FightEntity winner();
    public abstract void nextTurn();
}
