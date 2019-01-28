import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String name = returnName(reader);
        Gladiator player = makeGladiator(name, reader);
        beginGame(player,reader);

        System.out.println(player.name);
    }

    public static void beginGame(Gladiator player, Scanner reader){
        int round = 1;
        ArrayList<String> battleLog = new ArrayList<>();

        while (!player.isDead()) {
            Gladiator enemy = Enemy.generateNextEnemy(player, round);
            player.regenerate();
            enemy.regenerate();
            enemy.health -= 50;
            battle(player, reader, enemy, battleLog);
            round++;
            player.levelUp();
        }
        System.out.println("You died!");
        System.exit(0);
    }

    public static void battle(Gladiator player, Scanner reader, Gladiator enemy, ArrayList<String> battleLog){
        boolean playerAttackFirst = Utils.flipCoin();
        if (playerAttackFirst){
            while (!player.isDead() && !enemy.isDead()) {
                Utils.clearConsole();
                displayOpponents(player, enemy);
                playerBattleChoice(player, reader, enemy,  battleLog);
                Enemy.botChoice(enemy, player,  battleLog);
            }
        }else {
            while (!player.isDead() && !enemy.isDead()) {
                Utils.clearConsole();
                displayOpponents(player, enemy);
                Enemy.botChoice(enemy, player,  battleLog);
                playerBattleChoice(player, reader, enemy,  battleLog);
            }

        }
    }

    public static void displayBattleLog(ArrayList<String> battleLog){
        String header = "----Battle Log----";
        for (String log : battleLog){
            header += "\n"+ log;
        }
        System.out.println(header);
    }

    public static void displayOpponents(Gladiator player, Gladiator enemy){
        String bar = "________________________________________\n";
        String singleFormat = "%-20s |  %s\n";
        String healthFormat = "Health: %-4s/ %-6s |  Health: %-4s/ %s\n";
        String manaFormat = "Mana: %-4s/ %-8s |  Mana: %-4s/ %s\n";

        String finalFormat = bar + singleFormat + healthFormat + manaFormat + bar;
        System.out.printf(
                finalFormat,
                player.name,
                enemy.name,
                player.health,
                player.maxHealth,
                enemy.health,
                enemy.maxHealth,
                player.mana,
                player.maxMana,
                enemy.mana,
                enemy.maxMana
                );
    }


    public static void playerBattleChoice(Gladiator player, Scanner reader, Gladiator enemy, ArrayList<String> battleLog){
        while (true){
            System.out.printf("[1] Attack [2] Cast Spell (%s) [3] Rest ", player.manaCost);
            int choice = reader.nextInt();
            if (choice == 1){
                player.attack(enemy);
                battleLog.add(player.name + " Attacked" + enemy.name);
                break;
            }else if (choice == 2){
                if (player.mana >= player.manaCost){
                    player.castSpell(enemy);
                    battleLog.add(player.name + " Casted" + player.spellName);
                    break;
                }
            }else if (choice == 3){
                player.rest();
                battleLog.add(player.name+ " Rested");
                break;
            }
        }
    }

    public static String returnName(Scanner reader){
        while (true){
            System.out.print("Enter Your Name: ");
            String name = reader.nextLine().trim();
            if (name.length() <= 15){
                return name;
            }else{
                System.out.println("\nName must be between 1-15 characters!\n");
            }
        }
    }

    public static Gladiator makeGladiator(String name, Scanner reader){
        System.out.println("Choose a Class\n[1]Warrior");
        while (true){
            System.out.print("Choice: ");
            String classNum = reader.nextLine().trim();
            if (classNum.equals("1")){
                return new Warrior(name);
            }
        }
    }
}
