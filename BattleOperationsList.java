package JavaBattleSim;

import java.util.Random;
import java.util.Scanner;

public enum BattleOperationsList implements CombatAction{ // this one is for players;
    fight,block,use_item,run,pass,heal,weapon_effect,armor_effect //used by normalplayers and basic monsters
    ,prep,selfprep,selfspecialmove,specialmove; //used by complexmonsters
    public static BattleOperationsList decipher(String other){
        for (BattleOperationsList battleoperation:BattleOperationsList.values()){
            if (other.equalsIgnoreCase(battleoperation.toString())){
                return battleoperation;
            }
        }
        return null;
    }
    public static void DecideActionInteractive(Player me, FightEntity enemy, Battle battle) {
        System.out.println("turn of "+me+" \nwhat will you do? (fight,block,heal,use_item,weapon_effect,armor_effect,run,pass)");
        Scanner s = new Scanner(System.in);
        BattleOperationsList battleOperation = BattleOperationsList.decipher(s.nextLine());
        if (battleOperation == null) {
            throw new InvalidFightMove("this is not a move you have");
        }
        if (!battleOperation.toString().equalsIgnoreCase("use_item")) {
            BattleOperationsList.SimpleBattleAction(me, enemy, battleOperation, battle, null);
        } else {
            System.out.println("what item do you wish to use");
            Item item = me.items.getItem(Items.decipher(s.nextLine()));
            BattleOperationsList.SimpleBattleAction(me, enemy, battleOperation, battle, item);
        }
    }
    public static void SimpleBattleAction(Player fighter,FightEntity fighter2,BattleOperationsList action,Battle battle,Item item){
        if (action==BattleOperationsList.fight){
            SimpleBattleAttack(fighter,fighter2);
        }
        else if (action==BattleOperationsList.block){
            SimpleBattleBlock(fighter);
        }
        else if (action==BattleOperationsList.run){
            SimpleBattleRun(fighter,battle);
        }
        else if (action==BattleOperationsList.use_item){
            SimpleBattleUseItem(fighter,item);
        }
        else if (action==pass){

        }else if (action==heal){
            heal  (fighter);
        }else if (action==weapon_effect){
            fighter.weaponEffect();
        }else if (action==armor_effect){
            fighter.armorEffect();
        }else {throw new InvalidFightMove("this is not a move you have");}
    }
    private static void SimpleBattleUseItem(FightEntity fighter,Item item) {
        System.out.println(fighter+" used "+item);
        fighter.useItem(item);
    }
    private static void SimpleBattleRun(FightEntity fighter, Battle battle) {
        System.out.println(fighter+" attempted to run");
        fighter.run(battle);
    }
    private static void SimpleBattleBlock(FightEntity fighter) {
        System.out.println(fighter.name+"("+fighter.hp+")"+" is blocking");
        fighter.block();
    }
    private static void SimpleBattleAttack(FightEntity fighter,FightEntity fighter2) {
        System.out.println(fighter+" is attacking "+fighter2);
        fighter.fight(fighter2);
    }
    private static void heal(Player entity){
        System.out.println(entity+" healed itself ");
        entity.heal();
    }
    //everthing below is related to basic monsters, juggerants , eastrenwarriors and Firewielders, the original enemies i made.
    public static void monsterSimpleBattleAction(SimpleMonster me,FightEntity enemy){
        BattleOperationsList action=null;
        Item item=null;
        if (me.hp>=me.maxhp*0.5){
             action=fight;
        }else {
            int chance=new Random().nextInt(0,6);
            if (chance<=3){
                 action=fight;
            }
            if (chance==4){
                 action=block;
            }
            if (chance==5){
                item=new HealingPotion();
                me.addItem(item);
                action=use_item;
            }
        }
        if (action==BattleOperationsList.fight){
            SimpleBattleAttack(me,enemy);
        }
        if (action==BattleOperationsList.block){
            SimpleBattleBlock(me);
        }
        if (action==BattleOperationsList.use_item){
            SimpleBattleUseItem(me, item);
        }
    }
    public static void monsterSimpleBattleAction(JuggernatMonster me,FightEntity enemy){ // for Juggernat enemy
        BattleOperationsList action=null;
        int chance;
        if (me.hp > me.maxhp * 0.3) {
            if (me.preping) {
                action = BattleOperationsList.specialmove;
            }
            else if (me.selfprep) {
                action = BattleOperationsList.selfspecialmove;
            }
            else {
                // Logic: 70% chance to Prep/Buff, 30% chance to Fight/Block
                chance = new Random().nextInt(1, 11);

                if (chance <= 7) {
                    int coin = new Random().nextInt(0, 2);
                    if (coin == 1) {
                        action = BattleOperationsList.prep;
                    } else {
                        action = BattleOperationsList.selfprep;
                    }
                } else {
                    int subChance = new Random().nextInt(1, 5);
                    if (subChance <= 3) {
                        action = BattleOperationsList.fight;
                    } else {
                        action = BattleOperationsList.block;
                    }
                }
            }
        }
        else {
            if (me.preping) {
                action = BattleOperationsList.specialmove;
            } else {
                 chance = new Random().nextInt(0, 2);
                if (chance == 0) {
                    action = BattleOperationsList.fight;
                } else {
                    action = BattleOperationsList.prep;
                }
            }
        }
        if (action==BattleOperationsList.fight){
            SimpleBattleAttack(me,enemy);
        }
        if (action==BattleOperationsList.block){
            SimpleBattleBlock(me);
        }
        if (action==BattleOperationsList.prep){
            System.out.println(me+" is preparing for a HUGE attack");
            prep(me);
        }
        if (action==BattleOperationsList.selfprep){
            System.out.println(me+" is preparing to buff himself ");
            me.selfprep=true;
        }
        if (action==BattleOperationsList.specialmove){
            System.out.println(me+" has attacked "+enemy+" with a HUGE strike");
            specialMove(me,enemy);
        }
        if (action==BattleOperationsList.selfspecialmove){
            System.out.println(me+"has buffed himself! +15 to damage");
            selfSpecialMove(me);
        }

    }
    public static void monsterSimpleBattleAction(FireWielderMonster me,FightEntity enemy){
        BattleOperationsList action=null;
        int chance=0;
        if (me.preping){
            action=specialmove;
        }else if (enemy.hp<enemy.maxhp*0.25){
            action=prep;
        }else{
            chance = new Random().nextInt(1, 11);
            if (chance <= 7) {
                action = fight;
            }
            if (chance > 7) {
                action = selfspecialmove;
            }
        }
        if (action==BattleOperationsList.fight){
            SimpleBattleAttack(me,enemy);
        }
        if (action==BattleOperationsList.prep){
            System.out.println(me+" is preparing an execute spell! heal up!");
            prep(me);
        }
        if (action==specialmove){
            System.out.println(me+" has attempted to execute "+enemy);
            specialMove(me,enemy);
        }
        if (action==selfspecialmove){
            System.out.println(me+"has raised a temporary fire shield! beware, he cant hold it for long");
            selfSpecialMove(me);
        }
    }
    public static void monsterSimpleBattleAction(EasternWarrior me,FightEntity enemy){
        BattleOperationsList action=null;
        int chance;
        if (me.preping){
            action=specialmove;
        }else{
            chance=new Random().nextInt(0,101); // for percents from 1% to a 100%
            if (chance>=90){
                action=block;
            }
            else if (chance<=40){
                action=selfspecialmove;
            }
            else if (chance>40 && chance<=60){
                System.out.println(me+" is preparing a powerful slashing attack");
                action=prep;
            }else {
                action=fight;
            }

        }
        //with eastren warrior i made it so the callouts are inside the class itself. i think this is better for the future overall.
        if (action==BattleOperationsList.fight){
            EasternWarriorFight(me,enemy);
        }
        if (action==BattleOperationsList.prep){
            prep(me);
        }
        if (action==specialmove){
            specialMove(me,enemy);
        }
        if (action==selfspecialmove){
            selfSpecialMove(me);
        }
        if (action==block){
            SimpleBattleBlock(me);
        }
    }
    private static void EasternWarriorFight(EasternWarrior me,FightEntity enemy){
        System.out.println(me+" has doubled slashed "+enemy);
        me.fight(enemy);
    }


    private static void prep(ComplexMonster me){
        me.preping=true;
    }
    private static void selfSpecialMove(ComplexMonster me){
        me.specialmoves();
    }
    private static void specialMove(ComplexMonster me,FightEntity enemy){
        me.specialmoves(enemy);
    }
}
