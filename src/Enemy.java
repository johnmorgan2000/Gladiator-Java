abstract class Enemy {
    public static Gladiator makeEnemy(String name, int level, int classNum){
        Gladiator gladiator;

        while (true){
            if (classNum == 1 ){
                gladiator = new Warrior(name);
                break;
            }
        }
        getToLevel(gladiator, level);
        return gladiator;
    }

    public static void botChoice(Gladiator enemy, Gladiator player){
        if (player.health <= enemy.atkLow){
            enemy.attack(player);
        } else if (enemy.mana >= enemy.manaCost){
            enemy.castSpell(player);
        }else if (enemy.mana + enemy.manaRefillRate >= enemy.manaCost ){
            enemy.regenerate();
        }else{
            enemy.attack(player);
        }
    }

    public static void getToLevel(Gladiator g, int level){
        for (int i=0; i<=level; i++){
            g.levelUp();
        }
    }
}
