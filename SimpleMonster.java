package JavaBattleSim;

public class SimpleMonster extends Monster {
    public SimpleMonster(String name,int lv,int hp,int dmg,int armor){
        super(name,lv,hp,armor,dmg);
    }

    @Override
    public void MonsterDesicion(FightEntity enemy) {
        BattleOperationsList.monsterSimpleBattleAction(this,enemy);
    }
}
