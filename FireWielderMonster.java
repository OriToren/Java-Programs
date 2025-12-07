package JavaBattleSim;

import static JavaBattleSim.BattleOperationsList.monsterSimpleBattleAction;

public class FireWielderMonster extends ComplexMonster {
    boolean fireshield=false;
    int hpdiffrent=0;
    final int fireshieldbonus=this.getLv()*10;
    public FireWielderMonster(String name,int lv,int hp,int dmg,int armor){
        super(name,lv,hp,dmg,armor);
    }
    @Override
    public void specialmoves() {
    fireshield=true;
    hpdiffrent=hp;
    armor+=10;
    }
    @Override
    public void specialmoves(FightEntity enemy) {
    if (preping){
        if (enemy.hp<enemy.maxhp*0.25){
            enemy.die();
            System.out.println("Execution successful,subject was too weak to handle");
        }else {
            System.out.println("Execution failed,subject was too healthy");
            preping = false;
        }
    }
    }

    @Override
    public void MonsterDesicion(FightEntity enemy) {
        BattleOperationsList.monsterSimpleBattleAction(this,enemy);
    }
    @Override
    public  void resetTurn(){
        if (fireshield){
            if (hpdiffrent>hp){
                System.out.println("FireShield was destroyed!its fire enhanced "+name+" hp!");
                hp=hpdiffrent+fireshieldbonus;
                if (hp>maxhp){
                    maxhp=hp;
                }
                fireshield=false;
                armor-=10;
            }else {
                fireshield = false;
                armor-=10;
            }
            }

    checkForEffects();
    }

}
