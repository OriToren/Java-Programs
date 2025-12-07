package JavaBattleSim;

public class Player extends FightEntity implements CanHeal {
    Weapon weapon;
    Armor bodyarmor;
    final int dmggrowth=3;
    public Player(String name, int lv, int hp, Weapon weapon, Armor bodyarmor) {
        super(name, lv, hp); // Optional: if you want to use the FightEntity super constructor
        this.name = name;
        this.lv = lv;
        this.hp = hp;
        this.maxhp = hp;
        this.weapon = weapon;
        this.bodyarmor = bodyarmor;
        this.dmg = (lv * dmggrowth) + weapon.dmg;
        this.armor = bodyarmor.Armor_armor;
        this.infight = false;
        this.blocking = false;
        this.items = new BackPack<Item>();
    }

    public Player(String name, int lv, int hp, Weapon weapon) {
        this(name, lv, hp, weapon, new BasicShirt());
    }

    public Player(String name, int lv, int hp, Armor bodyarmor) {
        this(name, lv, hp, new BareHanded(), bodyarmor);
    }

    public Player(String name, int lv, int hp) {
        this(name, lv, hp, new BareHanded(), new BasicShirt());
    }

    // 5. Spawn/Default (Defaults to Lvl 1, 100 HP, BareHanded, BasicShirt)
    public Player(String name) {
        this(name, 1, 100, new BareHanded(), new BasicShirt());
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
        this.dmg = lv * dmggrowth + weapon.dmg;
    }

    public void dropWeapon() {
        this.weapon = new BareHanded();
        this.dmg = lv * dmggrowth;
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
