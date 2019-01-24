public class Warrior extends Gladiator{
    public Warrior() {
        this.setMana(40);
        this.setManaCost(30);
        this.setDefense(25);
        this.setAtkHigh(20);
        this.setAtkLow(20);
        this.setSpellName("Full Power Smash");
    }

    @Override
    public void castSpell(Gladiator enemy) {
        enemy.setHealth(enemy.getHealth()-this.getAtkHigh());
        enemy.setMana(enemy.getMana()-enemy.getManaCost());
    }

    @Override
    void levelUp() {

    }


}
