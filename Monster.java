package JavaBattleSim;

public  abstract class Monster extends FightEntity implements MonsterCombat {
    public Monster(String name,int lv,int hp,int dmg,int armor){
        super(name,lv,hp,armor,dmg);
    }
    public Monster(String name, int lv, int hp){
        super(name,lv,hp);
    }
    public Monster(){}
    @Override
    public void useItem(Item item) {
        if (items.getItem(item) != null){
            item.effect(this);
            items.remove(item);
            checkHp();
        }
        else {throw new InvalidItemMove("you dont have this item in your inventory");}
    }
    @Override
    public void addItem(Item item){
        items.add(item);
    }
    public abstract void MonsterDesicion(FightEntity enemy);

}



