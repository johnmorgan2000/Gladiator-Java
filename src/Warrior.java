public class Warrior extends Gladiator{
    public Warrior(String name) {
        super(name);
        this.mana = 40;
        this.manaCost= 30;
        this.defense = 4;
        this.atkHigh= 20;
        this.atkLow= 10;
        this.spellName = "Full Power Smash";
    }

    @Override
    public void castSpell(Gladiator enemy) {
        enemy.health -= this.atkHigh;
        enemy.mana -= enemy.manaCost;
    }

    @Override
    void levelUp() {
        this.level += 1;
        this.health += 10;
        this.mana += 5;
        this.defense += 3;
        this.atkHigh += 2;
        this.atkLow += 2;



    }


}
