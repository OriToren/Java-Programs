package JavaBattleSim;

public class StackingSpear extends Weapon {
    public StackingSpear(){
        this.dmg=18;
    }
    @Override
    public boolean effect(Player entity) {
        if (entity.infight) {
            entity.dmg += 2;
            return true;
        }
        return false;
    }
    @Override
    public boolean isThereEffect(){
        return true;
    }
    @Override
    public String printEffect(){
        return "permanently +2 to dmg";
    }
    @Override
    public String toString(){
        return "StackingSpear";
    }
}
