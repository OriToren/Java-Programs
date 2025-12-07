package JavaBattleSim;

import java.util.ArrayList;

public abstract class Battle implements BattleOperations {
    ArrayList<FightEntity> opps;
    int turn=-1;
    Battle(ArrayList<FightEntity> opps){
        this.opps=opps;
    }
    Battle(){}

    public ArrayList<FightEntity> getOpps() {
        return opps;
    }
    @Override
    public void startBattle(){
        if (turn==-1){
            turn++;
            for (FightEntity fighter:this.opps){
                fighter.infight=true;
            }

        }
    }

    public boolean hasStarted() {
        return (turn != -1);
    }

    public int getTurn() {
        return turn;
    }
    @Override
    public void nextTurn(){
        turn++;
    }

    public void removeFighter(FightEntity fighter){
        opps.remove(fighter);
    }

    public abstract FightEntity winner();

    public abstract boolean didEnd();
    public abstract void endBattle();
    void DecideActionInteractive(Player me, FightEntity enemy){
        if (me.infight) {
            me.resetTurn();
            BattleOperationsList.DecideActionInteractive(me, enemy, this);
            nextTurn();
        }
        else {nextTurn();}
    }
    void DecideAction(Monster me, FightEntity enemy){
        if (me.infight) {
            me.resetTurn();
            me.MonsterDesicion(enemy);
            nextTurn();
        }
        else {nextTurn();}
    }

}
