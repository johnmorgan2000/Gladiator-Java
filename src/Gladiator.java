abstract class Gladiator {
    private int health;
    private int mana;
    private int defense;

    Gladiator(){
        this.health = 100;
        this.mana = 100;
        this.defense = 100;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isDead(){
        if (this.getHealth() <= 0) {
            this.setHealth(0);
            return true;
        } else {
            return false;
        }
    }
    
}
