import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void saveGame(String[] args) {

        // java.io.File file = new java.io.File("C:\\Users\\Asus\\Desktop\\Games\\savegames");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save1.dat"))) {
            GameProgress gameProgress1 = new GameProgress(94, 10, 2, 254.22);
            oos.writeObject(gameProgress1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save2.dat"))) {
            GameProgress gameProgress2 = new GameProgress(88, 12, 3, 432.56);
            oos.writeObject(gameProgress2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save3.dat"))) {
            GameProgress gameProgress3 = new GameProgress(50, 8, 4, 456.78);
            oos.writeObject(gameProgress3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
