import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class Solution {
    public static void rename(String before, String after) {
        if (!(new File(before).exists())) {
            System.out.println("File not found.");
            return;
        }
        Path p1 = Paths.get(before);
        Path p2 = Paths.get(after);
        p1.toFile().renameTo(Paths.get(p1.getParent().toString()
                + "\\" + p2.getFileName().toString()).toFile());
    }

    public static void copy(String existsFile, String dir) {
        try {
            if (!(new File(existsFile).exists())) {
                System.out.println("File not found.");
                return;
            }
            Path p = Paths.get(existsFile);
            createDir(dir);
            Files.copy(Paths.get(existsFile), Paths.get(dir
                    + "\\" + p.getFileName()), StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException | SecurityException e) {
            System.out.println("Incorrect path.");
        }
    }

    public static void createDir(String path) {
        try {
            Path p = Paths.get(path);
            Path temp = p.getRoot();
            for (Path pa : p) {
                temp = Paths.get(temp.toString(), pa.toString());
                if (temp.toFile().exists() && !temp.toFile().isDirectory()) {
                    temp.toFile().mkdir();
                    continue;
                }
                if (!temp.toFile().exists()) {
                    temp.toFile().mkdir();
                }
            }
        } catch (Exception e) {
            System.out.println("Incorrect path.");
        }
    }

    public static void createFile(String path) {
        try {
            Path p = Paths.get(path);
            if (p.toFile().exists()) {
                System.out.println("File already exists.");
                return;
            }
            createDir(p.getParent().toString());
            (new File(path)).createNewFile();

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

    public static void toZip(String zipPath, String contentsPath) throws IOException {
        final Path zip = Paths.get(zipPath);
        Path contents = Paths.get(contentsPath);
        if (Files.exists(zip)) {
            throw new FileAlreadyExistsException(zip.toString());
        }
        if (!Files.exists(contents)) {
            throw new FileNotFoundException("The location to zip must exist");
        }
        final Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        final URI uri = URI.create("jar:file:/" + zip.toString().replace("\\", "/"));
        try (final FileSystem zipFileSystem = FileSystems.newFileSystem(uri, env);
             final Stream<Path> files = Files.walk(contents)) {
            final Path root = zipFileSystem.getPath("/");
            files.forEach(file -> {
                try {
                    copyToZip(root, contents, file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }


    private static void copyToZip(final Path root, final Path contents, final Path file) throws IOException {
        final Path to = root.resolve(contents.relativize(file).toString());
        if (Files.isDirectory(file)) {
            Files.createDirectories(to);
        } else {
            Files.copy(file, to);
        }
    }

    public static void unzip(String zipFile, String extractFolder) {
        try {
            int BUFFER = 2048;
            File file = new File(zipFile);

            ZipFile zip = new ZipFile(file);
            String newPath = extractFolder;

            new File(newPath).mkdir();
            Enumeration zipFileEntries = zip.entries();

            // Process each entry
            while (zipFileEntries.hasMoreElements()) {
                // grab a zip file entry
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                String currentEntry = entry.getName();

                File destFile = new File(newPath, currentEntry);
                //destFile = new File(newPath, destFile.getName());
                File destinationParent = destFile.getParentFile();

                // create the parent directory structure if needed
                destinationParent.mkdirs();

                if (!entry.isDirectory()) {
                    BufferedInputStream is = new BufferedInputStream(zip
                            .getInputStream(entry));
                    int currentByte;
                    // establish buffer for writing file
                    byte data[] = new byte[BUFFER];

                    // write the current file to disk
                    FileOutputStream fos = new FileOutputStream(destFile);
                    BufferedOutputStream dest = new BufferedOutputStream(fos,
                            BUFFER);

                    // read and write until last byte is encountered
                    while ((currentByte = is.read(data, 0, BUFFER)) != -1) dest.write(data, 0, currentByte);
                    dest.flush();
                    dest.close();
                    is.close();
                }


            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
