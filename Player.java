package JavaBattleSim;

public class Player extends FightEntity implements CanHeal {
    Weapon weapon;
    Armor bodyarmor;
    final int dmggrowth=3;
    public Player(String name) {
        super();
        spawn(name);
    }

    public Player(String name, int lv, int hp, Armor bodyarmor) { //i should have probablly used super for these but that was early in the development so il excuse myself
        //il change these later.
        this.name = name;
        this.lv = lv;
        this.hp = hp;
        this.maxhp = hp;
        this.weapon = new BareHanded();
        this.dmg = lv * dmggrowth;
        this.bodyarmor = bodyarmor;
        this.armor = bodyarmor.Armor_armor;
        this.infight = false;
        this.blocking = false;
        this.items = new BackPack<Item>();
    }

    public Player(String name, int lv, int hp, Weapon weapon, Armor bodyarmor) {
        this.name = name;
        this.lv = lv;
        this.hp = hp;
        this.maxhp = hp;
        this.armor = bodyarmor.Armor_armor;
        this.bodyarmor = bodyarmor;
        this.infight = false;
        this.blocking = false;
        this.items = new BackPack<Item>();
        this.weapon = weapon;
        this.dmg = lv * dmggrowth + weapon.dmg;

    }

    public Player(String name, int lv, int hp, Weapon weapon) {
        this.name = name;
        this.lv = lv;
        this.hp = hp;
        this.maxhp = hp;
        this.armor = new BasicShirt().Armor_armor;
        this.bodyarmor = new BasicShirt();
        this.infight = false;
        this.blocking = false;
        this.items = new BackPack<Item>();
        this.weapon = weapon;
        this.dmg = lv * dmggrowth + weapon.dmg;

    }

    public Player(String name, int lv, int hp) {
        this.name = name;
        this.lv = lv;
        this.hp = hp;
        this.maxhp=hp;
        this.weapon=new BareHanded();
        this.bodyarmor=new BasicShirt();
        this.dmg= weapon.dmg+lv*dmggrowth;
        this.armor= bodyarmor.Armor_armor;
        this.infight=false;
        this.blocking=false;
        this.items=new BackPack<Item>();


    }

    private void spawn(String name) {
        this.name = name;
        this.lv = 1;
        this.hp = 100;
        this.maxhp = 100;
        this.weapon = new BareHanded();
        this.dmg = lv * dmggrowth + weapon.dmg;
        this.armor = 10;
        this.infight = false;
        this.blocking = false;
        this.items = new BackPack<Item>();
    }

    @Override
    public void useItem(Item item) {
        if (items.getItem(item) != null) {
            item.effect(this);
            items.remove(item);
            checkHp();
        } else {
            throw new InvalidItemMove("you dont have this item in your inventory");
        }
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    public void heal() {
        hp += lv * 5; //this will be the default heal for now.
        checkHp();
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.dmg = lv * 4 + weapon.dmg;
    }

    public void dropWeapon() {
        this.weapon = new BareHanded();
        this.dmg = lv * 4;
    }

    public void weaponEffect() {
        weapon.effect(this);
        if (weapon.isThereEffect()) {
            System.out.println(weapon.printEffect());
        } else {
            System.out.println(weapon + " has no effect");
        }

    }

    public void equipArmor(Armor bodyarmor) {
        this.bodyarmor = bodyarmor;
        this.armor = bodyarmor.Armor_armor;
    }

    public void dropArmor() {
        this.bodyarmor = new BasicShirt();
        this.armor = 0;
    }

    public void armorEffect() {
        bodyarmor.effect(this);
        if (bodyarmor.isThereEffect()) {
            System.out.println(bodyarmor.printEffect());
        } else {
            System.out.println(bodyarmor + " has no effect");
        }
    }


    public void equipEquipment(Equipment equipment) {
        if (equipment instanceof Weapon) {
            equipWeapon((Weapon) equipment);
        } else {
            equipArmor((Armor) equipment);
        }
    }
}
