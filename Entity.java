package JavaBattleSim;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class Entity {
    String name;
    int lv;
    int hp;
    int maxhp;
    HashSet<StatusEffect> statusEffects=new HashSet<>();
    Entity(String name,int lv,int hp){
        this.name=name;
        this.lv=lv;
        this.hp=hp;
        this.maxhp=hp;
    }
    public Entity() {}
    public String toString(){
        String tostring=name+"("+hp+")";
        return tostring;
    }
    @Override
    public boolean equals(Object other){
        if (other instanceof  Entity){
            if (this.name.equals(((Entity) other).name ) && isAlive()==((Entity) other).isAlive() && this.lv==((Entity) other).lv && this.maxhp==((Entity) other).maxhp){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, lv,maxhp);
    }
    public void die(){
        this.hp=0;
    }

    public String getName() {
        return name;
    }

    public int getLv() {
        return lv;
    }

    public int getHp() {
        return hp;
    }
    void checkHp(){
        if (hp>maxhp){
            hp=maxhp;
        }
        if (hp<0){
            hp=0;
        }
    }

    public boolean isAlive() {
        return hp>0;
    }
    public int getMaxhp(){
        return maxhp;
    }
}
