import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;

public class DictionaryLookup {
    public static void main(String[] args) {
        System.out.println("...Welcome to the Dictionary...\n");
        System.out.println("Commands: ");
        System.out.println("search <word>");
        System.out.println("add <word> <definition>");
        System.out.println("remove <word>");
        System.out.println("print <word>");
        System.out.println("print all");
        System.out.println("exit\n");

        System.out.println("Loading Dictionary...");
        DictionaryBST bst = new DictionaryBST();
        readInFile("CS-Dictionary-full.txt", bst);
        System.out.println("Dictionary Loaded: " + bst.size() + " words\n");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            runCommand(command, bst);
        }
        scanner.close();
    }

    // method that reads a file in and puts the contents into a bst dictionary
    public static void readInFile(String filename, DictionaryBST bst) {
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.isEmpty()) {
                    continue;
                }
                String[] splitData = data.split(":");
                String key = splitData[0];
                String definition = splitData[1];
                bst.insert(key, definition);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
    }

    // method that takes a string command and runs the method associated with it in
    // the bst

    public static void runCommand(String command, DictionaryBST bst) {
        command = command.trim().replaceAll(" +", " ");
        String[] splitCommand = command.split(" ");
        String method = splitCommand[0];

        String key = splitCommand.length > 1 ? splitCommand[1] : "";
        String definition = splitCommand.length > 2 ? splitCommand[2] : "";
        try {

            if (method.equals("search")) {
                boolean result = bst.search(key);
                System.out.print("Dictionary ");
                System.out.println(result ? "Does Contain: " + key : "Doesn't Contain: " + key);
            } else if (method.equals("add")) {
                if (definition.isBlank())
                    throw new Exception("Could not add \"" + key + "\" Definition cannot be blank");

                bst.insert(key, definition);
                System.out.println("Added: \"" + key + "\" to dictionary");
            } else if (method.equals("remove")) {
                bst.remove(key);
            } else if (method.equals("print") && key.equals("all")) {
                bst.printDictionary();
            } else if (method.equals("print")) {
                bst.printDictionaryItem(key);
            } else {
                throw new Exception("Invalid Command\n");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
