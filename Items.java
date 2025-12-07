package JavaBattleSim;

public enum Items {
    healingpotion(new HealingPotion()), greaterhealingpotion(new GreaterHealingPotion());
    Item item;

    Items(Item item) {
        this.item = item;
    }
    @Override
    public String toString(){
        return item.toString();
    }

    public static Item decipher(String other) {
        for (Items item : Items.values()) {
            if (other.equalsIgnoreCase(item.toString())) {
                return item.item;
            }
        }
        return null;
    }
}
