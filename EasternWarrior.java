package JavaBattleSim;

import java.util.Random;


public class EasternWarrior extends ComplexMonster{
    boolean isevasive=false;
    int formerhp=hp;
    public EasternWarrior(String name, int lv, int hp, int dmg, int armor) {
        super(name, lv, hp, dmg, armor);
    }
    @Override
    public void specialmoves() {
        if (hp <= 10) {
            System.out.println(this + " has commited seppuku while trying to buff himself");
            this.die();
        } else {
            System.out.println(this + " has cut himself for greater Adrenaline and a chance to strike!");
            hp -= 10;
            preping = true;
            isevasive = true;
            formerhp = hp;
        }
    }
    @Override
    public void specialmoves(FightEntity enemy) {
        if (preping) {
            if (!enemy.blocking && !enemy.statusEffects.add(new Bleeding(2))) { // if this guy is already bleeding then just reset the bleed.
                enemy.statusEffects.remove(new Bleeding(2));
                enemy.statusEffects.add(new Bleeding(2));
                enemy.getDmg(dealDmgNoCrit());
                System.out.println(this + " has made " + enemy + " continue bleeding ");
                preping=false;
            } else if (!enemy.blocking) {
                System.out.println(this + " has applied bleed on " + enemy);
                enemy.getDmg(dealDmgNoCrit());
                preping=false;
            } else {
                System.out.println(enemy + " has blocked the slashing attack of " + this);
                preping=false;
            }
        }
    }
    @Override
    public void resetTurn(){
        if (blocking){
            blocking=false;
            armor-=60;
        }
        if (isevasive){
            if (formerhp>hp){
                int chance=new Random().nextInt(0,2);
                if (chance==1){
                    hp=formerhp;
                    System.out.println(this+" has dodged the attack!");
                    isevasive=false;
                }
            }
        }
        checkForEffects();

    }
    @Override
    public void fight(FightEntity enemy){
        if (enemy != null && infight) {
            enemy.getDmg((int) (dealDmg() * 0.5));
            enemy.getDmg((int) (dealDmg() * 0.5));
        }
    }
    @Override
    public void MonsterDesicion(FightEntity enemy) {
        BattleOperationsList.monsterSimpleBattleAction(this,enemy);
    }
}
