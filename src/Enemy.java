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

    public static void getToLevel(Gladiator g, int level){
        for (int i=0; i<=level; i++){
            g.levelUp();
        }
    }
}
