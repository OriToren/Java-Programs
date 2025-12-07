package JavaBattleSim;

public abstract class Weapon extends Equipment{
int dmg;
public abstract boolean effect(Player entity);
public abstract boolean isThereEffect();

}
