package JavaBattleSim;

public class SharpThrowingKnife extends Item{
    int turns=3;
    @Override
    public void effect(FightEntity player) {
        StatusEffectsHandler.reapplyStatusEffect(player.statusEffects,new Bleeding(turns));

    }
}
