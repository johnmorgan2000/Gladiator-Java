import java.util.Random;

public class Utils {
    static int randRange(int high, int low){
        Random rng = new Random();
        return rng.nextInt(high)+low;
    }

    public static void main(String[] args) {
        System.out.println(flipCoin());
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
