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
        while (!player.isDead()) {
            Gladiator enemy = Enemy.makeEnemy("Joe", 2, 1);
            player.regenerate();
            enemy.regenerate();
            battle(player, reader, enemy);
            round++;
            player.levelUp();
        }
        System.out.println("You died!");
        System.exit(0);
    }

    public static void battle(Gladiator player, Scanner reader, Gladiator enemy){
        boolean playerAttackFirst = Utils.flipCoin();
        if (playerAttackFirst){
            while (!player.isDead() && !enemy.isDead()) {
                displayOpponets(player, enemy);
                playerBattleChoice(player, reader, enemy);
                Enemy.botChoice(enemy, player);
            }
        }else {
            while (!player.isDead() && !enemy.isDead()) {
                displayOpponets(player, enemy);
                Enemy.botChoice(enemy, player);
                playerBattleChoice(player, reader, enemy);
            }

        }
    }

    public static void displayOpponets(Gladiator player, Gladiator enemy){
        String bar = "________________________________________\n";
        String singleFormat = "%-20s |  %s\n";
        String fractionalFormat = "%s/%-20s |  %s/%-20s\n";
        String finalFormat = bar+singleFormat+ fractionalFormat + bar;
        System.out.printf(finalFormat, player.name, enemy.name, player.health,player.maxHealth,enemy.health ,enemy.maxHealth );
    }


    public static void playerBattleChoice(Gladiator player, Scanner reader, Gladiator enemy){
        while (true){
            System.out.printf("[1] Attack [2] Cast Spell (%s) [3]Rest", player.manaCost);
            int choice = reader.nextInt();
            if (choice == 1){
                player.attack(enemy);
                break;
            }else if (choice == 2){
                if (player.mana > player.manaCost){
                    player.castSpell(enemy);
                    break;
                }
            }else if (choice == 3){
                player.rest();
                break;
            }
        }
    }


    public static String returnName(Scanner reader){
        while (true){
            System.out.println("Enter Your Name");
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
            int classNum = reader.nextInt();
            if (classNum == 1){
                return new Warrior(name);
            }
        }
    }
}
