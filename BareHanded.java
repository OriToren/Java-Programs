package JavaBattleSim;

public class BareHanded extends Weapon{
    public BareHanded(){
        this.dmg=0;
    }
    @Override
    public boolean effect(Player entity) {
        return false;
    }
    @Override
    public boolean isThereEffect(){
        return false;
    }
    @Override
    public String toString(){
        return "BareHands";
    }
}
