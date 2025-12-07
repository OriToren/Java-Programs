package JavaBattleSim;

public abstract class Weapon extends Equipment{
int dmg;
public abstract boolean effect(Player entity);
public boolean isThereEffect(){
    Player testplayer=new Player("testi");
    testplayer.equipWeapon(this);
    return testplayer.weapon.effect(testplayer);
}
}
