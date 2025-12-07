package JavaBattleSim;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class MultipleBattle extends Battle {
     int turncycle;
     FightEntity maintarget;
    public MultipleBattle(ArrayList<FightEntity> opps) {
        this.opps=opps;
        this.maintarget=opps.getFirst();
        this.turncycle=opps.size();
    }
    @Override
    public void startBattle(){
        if (turn==-1){
            turn++;
            for (FightEntity fighter:this.opps){
                fighter.infight=true;
            }

        }
        begin();
    }

    public void begin() {
        System.out.println("a battle between "+maintarget+" and "+opps());
        FightEntity attacker;
        FightEntity defender;
        while (!didEnd()){
            if (getTurn() % turncycle == 0){
                attacker=maintarget;
                defender=chooseWhoToAttack();
            }else {
                int whosturnisit=(getTurn() % turncycle);
                attacker=opps.get(whosturnisit);
                defender=maintarget;
            }
            if (Checker.isInteractive(attacker)) {
                DecideActionInteractive((Player) attacker, defender);
            } else {
                DecideAction((Monster) attacker, defender);
            }
        }
    }

    @Override
    public FightEntity winner() {
        if (Checker.isThereWinner(opps)){ //incase the lastcheck doesnt work
            return Checker.returnWinner(opps);
        }
        return null;
    }

    @Override
    public boolean didEnd() {
        if (winner() != null || !maintarget.infight){
            return true;
        }
        return false;
    }

    @Override
    public void endBattle() {
    if (!maintarget.infight){
        System.out.println(maintarget+" has died");
    }else {
        System.out.println(maintarget+" has won!");
    }
    }

    public String opps(){
        StringBuilder opponents=new StringBuilder();
        opponents.append("(");
        for (FightEntity fighter:opps){
            if (fighter.equals(maintarget)){
                continue;
            }
            if (fighter.infight && fighter.equals(opps.get(turncycle-1))) {
                opponents.append(fighter);
            }else {
                if (fighter.infight){
                    opponents.append(fighter);
                    opponents.append(',');
                }
            }

        }
        opponents.append(")");
        return opponents.toString();
    }
    public FightEntity chooseWhoToAttack(){
        if (!Checker.isInteractive(maintarget)){ // if the mainplayer isnt a player but an npc
            ArrayList<FightEntity> opponents=new ArrayList<>();
            opponents.addAll(opps);
            opponents.removeFirst();
            int choice=new Random().nextInt(0,opponents.size());
            FightEntity victum=opponents.get(choice);
            while (!victum.infight){ //check that man is not hitting a corpse
                opponents.remove(victum);
                choice=new Random().nextInt(0,opponents.size());
                victum=opponents.get(choice);
            }
            return victum;
        }
        System.out.println("from "+opps()+" who do you wish to attack? or pass to not attack anyone"); //Player.
        String choice=new Scanner(System.in).nextLine();
        for (FightEntity fighter:opps){
            if (choice.equalsIgnoreCase(fighter.name)){
                return fighter;
            }
        }
        if (choice.equalsIgnoreCase("pass")){
            return null;
        }
        throw new InvalidFightMove("cant attack someone who you're not fighting");
    }
    @Override
    public void nextTurn(){
        turn++;
        for (FightEntity fighter:opps){
            if (0>=fighter.hp){
                fighter.infight=false;
                if (fighter.equals(maintarget)){
                    endBattle();
                    return;
                }
            }
        }
    }
}
