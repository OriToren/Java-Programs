package JavaBattleSim;

import java.util.HashSet;

public class StatusEffectsHandler {
public static void resetStatusEffects(HashSet<StatusEffect> statusEffects){
    statusEffects.removeIf(effect -> effect.getTurns() <= 0);
    }

public static void applyStatusEffects(HashSet<StatusEffect> statusEffects,FightEntity fighter){
    for (StatusEffect statusEffect:statusEffects){
        if (statusEffect.getTurns()>0){
            statusEffect.effect(fighter);
        }
    }
}
public static boolean hasStatusEffect(HashSet<StatusEffect> statusEffects,FightEntity fighter) {
if (!statusEffects.isEmpty()){
    return true;
}
return false;
}
public static void reapplyStatusEffect(HashSet<StatusEffect> statusEffects,StatusEffect effect){
    if (!statusEffects.add(effect)){
        statusEffects.remove(effect);
        statusEffects.add(effect);
    }
}
}
