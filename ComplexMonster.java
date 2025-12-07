package JavaBattleSim;

public abstract class ComplexMonster extends Monster {
    boolean preping=false;

    public ComplexMonster(String name, int lv, int hp, int dmg, int armor) {
    super(name,lv,hp,dmg,armor);
    }

    public abstract void specialmoves();
    public abstract void specialmoves(FightEntity enemy);
}
