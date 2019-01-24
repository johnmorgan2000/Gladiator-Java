public class Warrior extends Gladiator{
    public Warrior() {
        this.mana = 40;
        this.manaCost= 30;
        this.defense = 25;
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
        this.health += 10;
    }


}
