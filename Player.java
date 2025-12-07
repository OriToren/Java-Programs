package JavaBattleSim;

public class Player extends FightEntity implements CanHeal {
    public  Player(String name){
        super();
        spawn(name);
    }
    public Player(String name,int lv,int hp,int dmg,int armor){
        this.name=name;
        this.lv=lv;
        this.hp=hp;
        this.maxhp=hp;
        this.dmg=dmg;
        this.armor=armor;
        this.infight=false;
        this.blocking=false;
        this.items=new BackPack<Item>();
    }
    private void spawn(String name){
        this.name=name;
        this.lv=1;
        this.hp=100;
        this.maxhp=100;
        this.dmg=20;
        this.armor=10;
        this.infight=false;
        this.blocking=false;
        this.items=new BackPack<Item>();
    }
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
    public void heal(){
        hp+=lv*5; //this will be the default heal for now.
        checkHp();
    }



}
