import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Solution.copy("d:\\3.txt", "d:\\1.txt");
        Solution.createFile("d:\\1\\2\\3\\4\\4dffgh.txt");
        Solution.viewAllCurrentDir("d:\\");
        Solution.viewFilesCurrentDir("d:\\");
        Solution.viewFilesDeep("d:\\1");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "exit";
        do {
            try {
                choice = reader.readLine();
            } catch (IOException e) {
                System.out.println("Incorrect command");
            }
            String[] command = choice.split("\\s+");
            switch (command[0]) {
                case "copy":
                    try {
                        Solution.copy(command[1], command[2]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect arguments.");
                    }
                    break;
                case "createFile":
                    try {
                        Solution.createFile(command[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect arguments.");
                    }
                    break;
                case "rename":
                    try {
                        Solution.rename(command[1], command[2]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect arguments.");
                    }
                    break;
                case "viewAllCurrentDir":
                    try {
                        Solution.viewAllCurrentDir(command[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect arguments.");
                    }
                case "viewFilesCurrentDir":
                    try {
                        Solution.viewFilesCurrentDir(command[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect arguments.");
                    }
                case "viewFilesDeep":
                    try {
                        Solution.viewFilesDeep(command[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect arguments.");
                    }
                case "help":
                    System.out.println("rename <full path> <full new name>\n" +
                            "copy <file> <dir to paste>\ncreateFile <path>\nviewAllCurrentDir <dir>\n" +
                            "viewviewFilesCurrentDir <dir>\nviewFilesDeep <dir>\nhelp");
            }
        } while (choice.toLowerCase().equals("exit"));
    }
}
