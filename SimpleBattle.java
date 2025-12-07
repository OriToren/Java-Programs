package JavaBattleSim;

import java.util.ArrayList;
import java.util.Scanner;
public class SimpleBattle extends Battle {
    FightEntity op1;
    FightEntity op2;
    public SimpleBattle(FightEntity op1, FightEntity op2) {
        super();
        this.opps = new ArrayList<FightEntity>(2);
        opps.add(op1);
        opps.add(op2);
        this.op1=opps.get(0);
        this.op2=opps.get(1);
    }
        @Override
        public void startBattle(){
            if (turn==-1 && Checker.checkIfAllAlive(opps)){
                turn++;
                for (FightEntity fighter:this.opps){
                    fighter.infight=true;

                }
                begin();
            }
        }
    private void begin() {
        System.out.println("1v1 fight between " + op1.name + " and " + op2.name);
        FightEntity attacker;
        FightEntity defender;
        while (!didEnd()) {
            if (getTurn() % 2 == 0) {
                attacker = op1;
                defender = op2;
            } else {
                attacker = op2;
                defender = op1;
            }
            if (Checker.isInteractive(attacker)) {
                DecideActionInteractive((Player) attacker, defender);
            } else {
                DecideAction((Monster) attacker, defender);
            }
        }
    }


    @Override
    public FightEntity winner(){
        if (opps.get(0).getHp()<=0){
            return opps.get(1);
        }
        if (opps.get(1).getHp()<=0){
            return opps.get(0);
        }
        if (Checker.isThereWinner(opps)){
            return Checker.returnWinner(opps);
        }
        return null;
    }
    public FightEntity loser() {
        if ((op1.hp<=0 || !op1.infight) && winner() != null){
            return op1;
        }else if (winner() != null) {
            return op2;
        }
        return null;

    }
    @Override
    public boolean didEnd(){
        if (winner() != null){
            endBattle();
            return true;
        }
        return false;
    }

    @Override
    public void endBattle() {
        System.out.println(winner()+" has won!");
        if (loser().infight) {
            loser().die();
        }
    }
    }

