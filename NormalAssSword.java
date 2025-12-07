package JavaBattleSim;

public class NormalAssSword extends Weapon{
    public NormalAssSword(){
        this.dmg=20;
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
        return "NormalAssSword";
    }
}
