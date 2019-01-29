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

    public static void adjustToDiffculty(Gladiator gladiator,int difficulty){
        int healthHandicap = 40;
        for (int i=0; i<difficulty; i++){
            healthHandicap -= 10;
        }
        gladiator.maxHealth -= healthHandicap;
    }

    public static Gladiator generateNextEnemy(Gladiator player, int round, int difficulty){
        String name = Utils.nameGenerator();
        int numberOfClasses = 1;
        int classNum = Utils.randRange(numberOfClasses, 1);

        if (round % 10 == 0){
            Gladiator g =  makeEnemy("(Boss) "+ name, round+1, classNum);
            adjustToDiffculty(g, difficulty);
            return g;
        }
        Gladiator g =  makeEnemy(name, round, classNum);
        adjustToDiffculty(g, difficulty);
        return g;
    }

    public static void botChoice(Gladiator enemy, Gladiator player, ArrayList<String> battleLog){
        if (player.health <= enemy.atkLow){
            enemy.attack(player);
            battleLog.add(enemy.name + " Attacked " + player.name);
        } else if (enemy.mana >= enemy.manaCost){
            enemy.castSpell(player);
            battleLog.add(enemy.name + " Casted " + enemy.spellName);
        }else if (enemy.mana + enemy.manaRefillRate >= enemy.manaCost ){
            enemy.mana += enemy.manaRefillRate;
            battleLog.add(enemy.name + " Rested");
        }else{
            enemy.attack(player);
            battleLog.add(enemy.name + " Attacked " + player.name);
        }
    }

    public static void getToLevel(Gladiator g, int level){
        for (int i=1; i<level; i++){
            g.levelUp();
        }
    }
}
