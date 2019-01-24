//Default Gladiator
abstract class Gladiator {
    private int health;
    private int mana;
    private int defense;
    private int atkLow;
    private int atkHigh;

    Gladiator(){
        this.health = 100;
        this.mana = 100;
        this.defense = 100;
        this.atkHigh = 20;
        this.atkLow = 5;
    }
    abstract void castSpell();

    public int attackDamage(){
        return Utils.randRange(this.getAtkHigh(), this.getAtkLow());
    }

    public boolean isDead(){
        if (this.getHealth() <= 0) {
            this.setHealth(0);
            return true;
        } else {
            return false;
        }
    }

    public int getAtkHigh() {
        return atkHigh;
    }

    public void setAtkHigh(int atkHigh) {
        this.atkHigh = atkHigh;
    }

    public int getAtkLow() {
        return atkLow;
    }

    public void setAtkLow(int atkLow) {
        this.atkLow = atkLow;
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


}
