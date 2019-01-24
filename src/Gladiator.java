//Default Gladiator
abstract class Gladiator {
    public String name;
    public int health;
    public int maxHealth;
    public int level;
    public int mana;
    public int maxMana;
    public int manaCost;
    public int manaRefillRate;
    public int defense;
    public int atkLow;
    public int atkHigh;
    public String spellName;

    Gladiator(String name){
        this.name = name;
        this.health = 100;
        this.maxHealth = 100;
        this.level = 1;
        this.mana = 100;
        this.maxMana = 100;
        this.manaCost = 10;
        this.manaRefillRate = 10;
        this.defense = 0;
        this.atkHigh = 20;
        this.atkLow = 5;
        this.spellName = "";

    }
    abstract void castSpell(Gladiator enemy);

    abstract void rest();

    public void attack(Gladiator enemy){
        enemy.health -= this.attackDamage() - enemy.defense;
        this.mana += Utils.randRange(15,5);
    }

    abstract void levelUp();

    public int attackDamage(){
        return Utils.randRange(atkHigh, atkLow);
    }

    public boolean isDead(){
        if (health <= 0) {
            health = 0;
            return true;
        } else {
            return false;
        }
    }

    public void regenerate(){
        this.health = this.maxHealth;
        this.mana = this.maxMana;
    }
}
