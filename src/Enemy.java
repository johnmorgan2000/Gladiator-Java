import java.util.ArrayList;

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

    public static Gladiator generateNextEnemy(Gladiator player, int round){
        String name = Utils.nameGenerator();
        int numberOfClasses = 1;
        int classNum = Utils.randRange(numberOfClasses, 1);

        if (round % 10 == 0){
            return makeEnemy("(Boss) "+ name, round+1, classNum);
        }
        return makeEnemy(name, round, classNum);
    }

    public static void botChoice(Gladiator enemy, Gladiator player, ArrayList<String> battleLog){
        if (player.health <= enemy.atkLow){
            enemy.attack(player);
            battleLog.add(enemy.name + " Attacked" + player.name);
        } else if (enemy.mana >= enemy.manaCost){
            enemy.castSpell(player);
            battleLog.add(enemy.name + " Casted" + enemy.spellName);
        }else if (enemy.mana + enemy.manaRefillRate >= enemy.manaCost ){
            enemy.mana += enemy.manaRefillRate;
            battleLog.add(enemy.name + " Rested");
        }else{
            enemy.attack(player);
            battleLog.add(enemy.name + " Attacked" + player.name);
        }
    }

    public static void getToLevel(Gladiator g, int level){
        for (int i=1; i<level; i++){
            g.levelUp();
        }
    }
}
