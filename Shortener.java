package JavaBattleSim;

import java.util.ArrayList;

public class Shortener {
    public static Player newplayer(String name){
        return new Player(name);
    }
    public static SimpleBattle simplebattle(FightEntity op1, FightEntity op2){
        return new SimpleBattle(op1,op2);
    }
    public static void startsimplebattle(FightEntity op1,FightEntity op2){
        new SimpleBattle(op1,op2).startBattle();
    }
    public static MultipleBattle doublebattle(FightEntity mainplayer,FightEntity op1,FightEntity op2){
        ArrayList<FightEntity> doubletrouble=new ArrayList<>();
        doubletrouble.add(mainplayer);
        doubletrouble.add(op1);
        doubletrouble.add(op2);
        return new MultipleBattle(doubletrouble);
    }
    public static MultipleBattle triplebattle(FightEntity mainplayer,FightEntity op1,FightEntity op2,FightEntity op3){
        ArrayList<FightEntity> tripletrouble=new ArrayList<>();
        tripletrouble.add(mainplayer);
        tripletrouble.add(op1);
        tripletrouble.add(op2);
        tripletrouble.add(op3);
        return new MultipleBattle(tripletrouble);
    }

}
