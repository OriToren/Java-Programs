package JavaBattleSim;

public class JuggernatMonster extends ComplexMonster {
    boolean selfprep=false;
    public JuggernatMonster(String name,int lv,int hp,int dmg,int armor){
        super(name,lv,hp,dmg,armor);
    }

    @Override
    public void MonsterDesicion(FightEntity enemy) {
        BattleOperationsList.monsterSimpleBattleAction(this,enemy);
    }
    @Override
    public void specialmoves() {
        if (selfprep){
            dmg+=15;
            selfprep=false;
        }
    }

    @Override
    public void specialmoves(FightEntity enemy) {
        if (preping && enemy != null){
            enemy.getDmg(dealDmgNoCrit()*2);
            preping=false;
        }
    }
}
