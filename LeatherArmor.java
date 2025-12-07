package JavaBattleSim;

public class LeatherArmor extends Armor{
    public LeatherArmor(){
        Armor_armor=10;
    }

    @Override
    public boolean effect(Player entity) {
        return false;
    }

    @Override
    public boolean isThereEffect() {
        return false;
    }
    @Override
    public String toString(){
        return "LeatherArmor";
    }
}
