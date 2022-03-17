import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class File {
    public static void main(String[] args) throws IOException {

        // 1. В папке Games создайте несколько директорий: src, res, savegames, temp.

        java.io.File dir = new java.io.File("res");
        if (dir.mkdir()) {
            System.out.println("Создана директория " + dir.getName());
        }
        java.io.File dir1 = new java.io.File("savegames");
        if (dir1.mkdir()) {
            System.out.println("Создана директория " + dir1.getName());
        }
        java.io.File dir2 = new java.io.File("temp");
        if (dir2.mkdir()) {
            System.out.println("Создана директория " + dir2.getName());
        }

        // 2. В каталоге src создайте две директории: main, test.

        java.io.File dir3 = new java.io.File("C:\\Users\\Asus\\Desktop\\Games\\src\\main");
        if (dir3.mkdir()) {
            System.out.println("Создана директория " + dir3.getName());
        }
        java.io.File dir4 = new java.io.File("C:\\Users\\Asus\\Desktop\\Games\\src\\test");
        if (dir4.mkdir()) {
            System.out.println("Создана директория " + dir4.getName());
        }

        // 3. В подкаталоге main создайте два файла: Main.java, Utils.java.

        try {
            java.io.File file1 = new java.io.File("C:\\Users\\Asus\\Desktop\\Games\\src\\test\\Main.java");
            if (file1.createNewFile())
                System.out.println("File created" + file1.getName());
            else
                System.out.println("File already exists");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            java.io.File file2 = new java.io.File("C:\\Users\\Asus\\Desktop\\Games\\src\\test\\Utils.java");
            if (file2.createNewFile())
                System.out.println("File created" + file2.getName());
            else
                System.out.println("File already exists");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. В каталог res создайте три директории: drawables, vectors, icons.

        java.io.File dir5 = new java.io.File("C:\\Users\\Asus\\Desktop\\Games\\res\\drawables");
        if (dir5.mkdir()) {
            System.out.println("Создана директория " + dir5.getName());
        }

        java.io.File dir6 = new java.io.File("C:\\Users\\Asus\\Desktop\\Games\\res\\vectors");
        if (dir6.mkdir()) {
            System.out.println("Создана директория " + dir6.getName());
        }

        java.io.File dir7 = new java.io.File("C:\\Users\\Asus\\Desktop\\Games\\res\\icons");
        if (dir7.mkdir()) {
            System.out.println("Создана директория " + dir7.getName());
        }

        //5. В директории temp создайте файл temp.txt.

        StringBuilder sb = new StringBuilder();
        sb.append("Создана директория " + dir.getName() + "\n");
        sb.append("Создана директория " + dir1.getName() + "\n");
        sb.append("Создана директория " + dir2.getName() + "\n");
        sb.append("Создана директория " + dir3.getName() + "\n");
        sb.append("Создана директория " + dir4.getName() + "\n");
        sb.append("File created" + "\n");
        sb.append("File created" + "\n");
        sb.append("Создана директория " + dir5.getName() + "\n");
        sb.append("Создана директория " + dir6.getName() + "\n");
        sb.append("Создана директория " + dir7.getName() + "\n");
        String result = sb.toString();
        //System.out.println(result);

        try {
            java.io.File file3 = new java.io.File("C:\\Users\\Asus\\Desktop\\Games\\src\\test\\temp.txt");
            file3.createNewFile();
            FileWriter writer = new FileWriter(file3);
            writer.write(result);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        saveGame();
        zipFiles();
        delete();
    }
    //Сохранение информации об игровом процессе

    public static void saveGame() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save1.dat"))) {
            GameProgress gameProgress1 = new GameProgress(94, 10, 2, 254.22);
            oos.writeObject(gameProgress1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save2.dat"))) {
            GameProgress gameProgress2 = new GameProgress(88, 12, 3, 432.56);
            oos2.writeObject(gameProgress2);
            oos2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save3.dat"))) {
            GameProgress gameProgress3 = new GameProgress(50, 8, 4, 456.78);
            oos3.writeObject(gameProgress3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Файлы сохранения созданы");
    }
    // Архивация инфрмации об игровом процессе

    public static void zipFiles() throws IOException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:\\Users\\Asus\\Desktop\\Games\\savegames\\zip.zip"));
             // архивируем первый файл
             FileInputStream fis1 = new FileInputStream("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save1.dat");
             FileInputStream fis2 = new FileInputStream("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save2.dat");
             FileInputStream fis3 = new FileInputStream("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save3.dat")) {
            ZipEntry entry = new ZipEntry("pasked_save1.dat");
            ZipEntry entry2 = new ZipEntry("pasked_save2.dat");
            ZipEntry entry3 = new ZipEntry("pasked_save3.dat");
            zout.putNextEntry(entry);
            zout.putNextEntry(entry2);
            zout.putNextEntry(entry3);
            byte[] buffer = new byte[fis1.available()];
            byte[] buffer2 = new byte[fis2.available()];
            byte[] buffer3 = new byte[fis2.available()];
            fis1.read(buffer);
            fis2.read(buffer2);
            fis3.read(buffer3);
            zout.write(buffer);
            zout.write(buffer2);
            zout.write(buffer3);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Файлы заархивированы");
    }

    // Удаление неархивированных файлов из папки
    public static void delete() {
        try {
            Files.delete(Paths.get("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save1.dat"));
        } catch (IOException x) {
            System.err.println(x);
        }

        try {
            Files.delete(Paths.get("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save2.dat"));
        } catch (IOException x) {
            System.err.println(x);
        }
        try {
            Files.delete(Paths.get("C:\\Users\\Asus\\Desktop\\Games\\savegames\\save3.dat"));
        } catch (IOException x) {
            System.err.println(x);
        }
        System.out.println("Ненужные файлы удалены");
    }
}
