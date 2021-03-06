import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        gameLoader(reader);
    }

    public static void gameLoader(Scanner reader){
        while (true){
            System.out.println("[1] New Game  [2] Load Game: ");
            String response = reader.nextLine().trim();
            if (response.equals("1") ){
                String name = returnName(reader);
                Gladiator player = makeGladiator(name, reader);
                beginGame(player,reader);
                break;
            }else if (response.equals("2")){
                System.out.println("Enter Save File Name: ");
                String name = reader.nextLine().trim();
                Gladiator player =  GladiatorSaver.load(name);
                if (player != null){
                    beginGame(player, reader);
                }
            }else{
                System.out.print("Enter a valid option!");
            }
        }

    }

    public static void beginGame(Gladiator player, Scanner reader){
        int round = player.level;
        ArrayList<String> battleLog = new ArrayList<>();
        int difficulty = chooseDifficulty(reader);

        while (!player.isDead()) {
            Gladiator enemy = Enemy.generateNextEnemy(player, round, difficulty);
            player.regenerate();
            enemy.regenerate();
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
                displayOpponents(player, enemy);
                playerBattleChoice(player, reader, enemy,  battleLog);
                Enemy.botChoice(enemy, player,  battleLog);
                displayBattleLog(battleLog);
            }
        }else {
            while (!player.isDead() && !enemy.isDead()) {
                displayOpponents(player, enemy);
                Enemy.botChoice(enemy, player,  battleLog);
                displayBattleLog(battleLog);
                playerBattleChoice(player, reader, enemy,  battleLog);
                displayBattleLog(battleLog);
            }

        }
    }

    public static void displayBattleLog(ArrayList<String> battleLog){
        if (battleLog.size() > 2){
            for (int i = 0; i < (battleLog.size() - 2); i++){
                battleLog.remove(0);
            }
        }
        String header = "----Battle Log----";
        for (String log : battleLog){
            header += "\n"+ log;
        }
        System.out.println(header + "\n------------------\n");


    }

    public static void displayOpponents(Gladiator player, Gladiator enemy){
        String bar = "________________________________________\n";
        String round="* Round %s *\n";
        String singleFormat = "%-20s |  %s\n";
        String healthFormat = "Health: %-4s/ %-6s |  Health: %-4s/ %s\n";
        String manaFormat = "Mana: %-4s/ %-8s |  Mana: %-4s/ %s\n";

        String finalFormat = bar + round + singleFormat + healthFormat + manaFormat + bar;
        System.out.printf(
                finalFormat,
                player.level,
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
            System.out.printf("[1] Attack [2] Cast Spell (%s) [3] Rest [4] Save/Quit: ", player.manaCost);
            String choice = reader.nextLine();
            if (choice.equals("1")){
                player.attack(enemy);
                battleLog.add(player.name + " Attacked " + enemy.name);
                break;
            }else if (choice.equals("2")){
                if (player.mana >= player.manaCost){
                    player.castSpell(enemy);
                    battleLog.add(player.name + " Casted " + player.spellName);
                    break;
                }
            }else if (choice.equals("3")){
                player.rest();
                battleLog.add(player.name+ " Rested");
                break;
            }else if (choice.equals("4")){
                boolean result =  GladiatorSaver.save(player);
                if (result){
                    System.exit(0);
                }
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
        System.out.println("Choose a Class\n\t[1]Warrior\n");
        while (true){
            System.out.print("Choice: ");
            String classNum = reader.nextLine().trim();
            if (classNum.equals("1")){
                return new Warrior(name);
            }
        }
    }

    public static int chooseDifficulty(Scanner reader){
        while (true){
            System.out.println("Choose a difficulty. 1-3 (Easiest)-(Hardest): ");
            String response = reader.nextLine();
            if (response.matches("-?\\d+")){
                int difficulty = Integer.parseInt(response);
                if (difficulty <= 3 && difficulty >= 1){
                    return difficulty;
                }
            }

        }

    }
}
