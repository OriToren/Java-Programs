package JavaBattleSim;

public abstract class HealingItem extends Item{
    int healing;
    public HealingItem(){}
    @Override
    public void effect(FightEntity player) {
            player.hp += healing;
    }


}
