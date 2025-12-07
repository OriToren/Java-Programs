package JavaBattleSim;

import java.util.Objects;

public abstract class StatusEffect {
    int turns;
    public StatusEffect(){
        turns=2;
    }
    public StatusEffect(int turns){
        this.turns=turns;
    }
    public abstract void effect(FightEntity fighter);
    @Override
    public boolean equals(Object other){
        if (other instanceof StatusEffect){
            if (this.getClass().equals(other.getClass())){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return java.util.Objects.hash(this.getClass());
    }
    public int getTurns(){
        return turns;
    }

}
