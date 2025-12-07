package JavaBattleSim;

public class PoisonBomb extends Item{
    int dps=8;
    public PoisonBomb(){}
    @Override
    public void effect(FightEntity player) { // doesnt damage the enemy btw! (i mean directily it goes give him poison)
        StatusEffectsHandler.reapplyStatusEffect(player.statusEffects,new Poisoned(dps));
    }
}
