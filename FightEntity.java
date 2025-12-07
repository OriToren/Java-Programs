package JavaBattleSim;

import java.util.ArrayList;
import java.util.Random;

public abstract class FightEntity extends Entity implements Fightable { //name,lv,hp,maxhp,dmg,armor,infight,blocking,multies,items
    int dmg;
    int armor;
    boolean infight;
    boolean blocking;
    BackPack<Item> items;
    final double armormultiplier = 1.75;
    final int critmultiplier = 2;

    FightEntity(String name, int lv, int hp, int dmg, int armor) {
        super(name, lv, hp);
        this.dmg = dmg;
        this.armor = armor;
        this.blocking = false;
        this.infight = false;
        this.items = new BackPack<Item>();
    }

    public FightEntity() {
        super();
    }

    public FightEntity(String name, int lv, int hp) {
        super(name, lv, hp);
        this.blocking = false;
        this.dmg = 35;
        this.armor = 10;
        this.items = null;
    }

    public boolean isFighting(Battle battle) {
        for (FightEntity fighter : battle.opps) {
            if (this.equals(fighter)) {
                infight = true;
                return true;
            }
        }
        infight = false;
        return false;
    }

    @Override
    public void die() {
        this.hp = 0;
        this.infight = false;
    }

    public void getDmg(int dmg) {
        Random randomdmg = new Random();
        int chance = randomdmg.nextInt(1, 9);
        if (chance <= 2) {
            hp -= (int) (dmg * ((1 - (armor * armormultiplier) / (100))));
        } else {
            hp -= (int) (dmg * ((1 - armor / 100.0)));
        }

    }

    public int dealDmg() {
        int chance = new Random().nextInt(1, 11);
        if (chance <= 1) {
            return dmg * critmultiplier;
        }
        return dmg;
    }

    public int dealDmgNoCrit() {
        return dmg;
    }

    @Override
    public void fight(FightEntity opp) {
        if (infight || opp != null) {
            opp.getDmg(dmg);
        }
    }

    @Override
    public void block() {
        if (infight) {
            armor += 60;
            this.blocking = true;
        }
    }

    @Override
    public abstract void useItem(Item item);

    @Override
    public abstract void addItem(Item item);

    @Override
    public void run(Battle battle) {
        if (isFighting(battle)) {
            Random rand = new Random();
            int chance = rand.nextInt(1, 11);
            if (chance > 6) {
                infight = false;
                System.out.println("escape attempt successful");
            }else {
                System.out.println("escape attempt failed");}
        }

    }

    @Override
    public void resetTurn() {
        if (blocking) {
            this.blocking = false;
            armor -= 60;
        }
        checkForEffects();
    }

    public void checkForEffects() {
        if (StatusEffectsHandler.hasStatusEffect(statusEffects, this)) {
            StatusEffectsHandler.applyStatusEffects(statusEffects, this);
            StatusEffectsHandler.resetStatusEffects(statusEffects);
        }
    }

    public int getDmg() {
        return dmg;
    }

    public int getArmor() {
        return armor;
    }

    public BackPack<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        String tostring;
        if (!StatusEffectsHandler.hasStatusEffect(statusEffects,this)){
             tostring = name + "(" + hp + ")";
        }else {
             StringBuilder builder=new StringBuilder();
             builder.append(name).append("(").append(hp).append(")");
             builder.append("[");
             for (StatusEffect effect:statusEffects){
                 builder.append(effect).append("(");
                 builder.append(effect.getTurns()).append(")");
             }
             builder.append(']');
             tostring=builder.toString();
        }
        return tostring;

    }
}

