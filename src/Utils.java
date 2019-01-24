import java.util.Random;

public class Utils {
    static int randRange(int high, int low){
        Random rng = new Random();
        return rng.nextInt(high)+low;
    }
}
