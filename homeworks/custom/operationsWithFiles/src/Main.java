import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "exit";
        System.out.println("rename <path> <new name>\n" +
                "copy <file> <dir to paste>\ncreateFile <path>\nviewAllCurrentDir <dir>\n" +
                "viewFilesCurrentDir <dir>\nviewFilesDeep <dir>\nhelp\nexit");
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
                case "createDir":
                    try {
                        Solution.createDir(command[1]);
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
                    break;
                case "viewFilesCurrentDir":
                    try {
                        Solution.viewFilesCurrentDir(command[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect arguments.");
                    }
                    break;
                case "viewFilesDeep":
                    try {
                        Solution.viewFilesDeep(command[1]);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect arguments.");
                    }
                    break;
                case "help":
                    System.out.println("rename <path> <new name>\n" +
                            "copy <file> <dir to paste>\ncreateFile <path>\nviewAllCurrentDir <dir>\n" +
                            "viewFilesCurrentDir <dir>\nviewFilesDeep <dir>\nhelp\nexit");
                    break;
                default:
                    System.out.println("Incorrect command.");
                    break;
            }
        } while (!choice.toLowerCase().equals("exit"));
    }
}
