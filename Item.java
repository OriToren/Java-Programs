package JavaBattleSim;

import java.util.Objects;

public abstract class Item {
    Item(){}
    public abstract void effect(FightEntity player);
    @Override
    public boolean equals(Object other){
        if (other instanceof Item){
            if (other.getClass().equals(this.getClass())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.getClass());
    }
}
