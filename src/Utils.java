import java.util.ArrayList;
import java.util.Random;

public class Utils {
    static int randRange(int high, int low){
        Random rng = new Random();
        return rng.nextInt(high)+low;
    }

    public static String nameGenerator(){
        ArrayList<String> title = new ArrayList<>();
        title.add("Master");
        title.add("Silly");
        title.add("Sensei");
        title.add("Mighty");
        title.add("Holy");

        ArrayList<String> name = new ArrayList<>();
        name.add("Killy");
        name.add("Bill");
        name.add("Coder");
        name.add("Yummy");
        name.add("Unknown");

        int indexOne = randRange(title.size(), 0);
        int indexTwo = randRange(name.size(), 0);

        return title.get(indexOne) + name.get(indexTwo);

    }




    static boolean flipCoin(){
        switch(randRange(2,1)){
            case 2:
                return true;
            case 1:
                return false;
        }
        return false;
    }
}
