package JavaBattleSim;

public class BasicShirt extends Armor{
    public BasicShirt(){
        Armor_armor=0;
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
        return "BasicShirt";
    }
}
