package JavaBattleSim;

public interface CombatAction {
    private static void SimpleBattleRun(FightEntity fighter, Battle battle) {
        fighter.run(battle);
    }
    private static void SimpleBattleBlock(FightEntity fighter) {
        fighter.block();
    }
    private static void SimpleBattleAttack(FightEntity fighter,FightEntity fighter2) {fighter2.getDmg(fighter.dealDmg());}
}
