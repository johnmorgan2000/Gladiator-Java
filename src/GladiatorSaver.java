import java.io.*;
import java.util.Scanner;

public class GladiatorSaver {
    public static boolean save(Gladiator gladiator){
        Scanner reader = new Scanner(System.in);
        System.out.print("Name of the save file: ");
        String name = reader.nextLine();
        try{
            File filename = new File("save_files/"+ name +".ser") ;
            filename.createNewFile();
            FileOutputStream fs = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(gladiator);
            os.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static Gladiator load(String name){
        try{
            File filename = new File("save_files/"+ name +".ser") ;
            if (filename.exists()){
                FileInputStream fs = new FileInputStream(filename);
                ObjectInputStream os = new ObjectInputStream(fs);
                Object restore =  os.readObject();
                os.close();
                return  (Gladiator) restore;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
