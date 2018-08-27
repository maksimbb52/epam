import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.stream.Stream;

public class Solution {
    public static void rename(String before, String after) {
        File file = new File(before);
        try {
            if (file.exists() && file.isFile()) {
                file.renameTo(new File(after));
                return;
            }
            System.out.println("File not exists");
        } catch (SecurityException e) {
            System.out.println("Incorrect one of paths.");
        }
    }

    public static void copy(String existsFile, String Dir) {
        File file = new File(existsFile);
        Path p = Paths.get(existsFile);
        try {
            if (file.exists() && file.isFile()) {
                Files.copy(Paths.get(existsFile), Paths.get(Dir + "\\" + p.getFileName()), StandardCopyOption.COPY_ATTRIBUTES);
                return;
            }
            System.out.println("File not exists.");
        } catch (IOException | SecurityException e) {
            System.out.println("Incorrect one of paths.");
        }
    }

    public static void createDir(String path) {
        Path p = Paths.get(path);
        Path temp = p.getRoot();
        for (Path pa: p) {
            temp = Paths.get(temp.toString(), pa.toString());
            System.out.println(temp.toString());
            if (temp.toFile().exists() && !temp.toFile().isDirectory()) {
                temp.toFile().mkdir();
                continue;
            }
            if (!temp.toFile().exists()) {
                temp.toFile().mkdir();
                continue;
            }
        }
    }

        public static void createFile(String path) {
        try {
            Path p = Paths.get(path);
            Path temp = p.getRoot();
            for (Path pa: p.getParent()) {
                temp = Paths.get(temp.toString(), pa.toString());
                System.out.println(temp.toString());
                if (temp.toFile().exists() && !temp.toFile().isDirectory()) {
                    temp.toFile().mkdir();
                    continue;
                }
                if (!temp.toFile().exists()) {
                    temp.toFile().mkdir();
                    continue;
                }
            }
            if (!p.toFile().exists()) {
                p.toFile().createNewFile();
                return;
            }
            System.out.println("File already exists");

        } catch (IOException e) {
            System.out.println("Incorrect path.");
        }
     }

    public static void viewAllCurrentDir(String path) {
        try (Stream<Path> paths = Files.list(Paths.get(path))) {
            paths
                    .forEach(System.out::println);
        } catch (IOException | NullPointerException e) {
            System.out.println("Incorrect path.");
        }
    }

    public static void viewFilesCurrentDir(String path) {
        try (Stream<Path> paths = Files.list(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException | NullPointerException e) {
            System.out.println("Incorrect path.");
        }
    }

    public static void viewFilesDeep(String path) {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException | NullPointerException e) {
            System.out.println("Incorrect path.");
        }
    }

}
