import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;

/**
 * DictionaryLookup.java
 * 
 * This class is a command line interface for the DictionaryBST class. It has
 * methods to read in a file, print the command list, and run a command. The
 * commands are search, add, remove, print, print all, and exit. The search
 * command searches for a word in the dictionary. The add command adds a word
 * and
 * definition to the dictionary. The remove command removes a word from the
 * dictionary. The print command prints a specific word and definition from the
 * dictionary. The print all command prints all the words and definitions in the
 * dictionary. The exit command exits the program.
 * 
 * The DictionaryLookup class uses the DictionaryBST class to create a binary
 * search tree dictionary. It reads in a file of words and definitions and adds
 * them to the dictionary. It then runs the command line interface for the
 * dictionary.
 * 
 * @author Alexander Trotter
 * @version 04/15/2021
 */
public class DictionaryLookup {

    /**
     * Main method that runs the command line interface for the dictionary
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("...Welcome to the Dictionary...\n");
        printCommandList();

        System.out.println("Loading Dictionary...");
        DictionaryBST bst = new DictionaryBST();
        readInFile("CS-Dictionary-full.txt", bst);
        System.out.println("Dictionary Loaded: " + bst.size() + " words\n");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printCommandList();
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            runCommand(command, bst);
        }
        scanner.close();
    }

    /**
     * Method that prints the command list
     */
    public static void printCommandList() {
        System.out.println("Commands: ");
        System.out.println("search <word>");
        System.out.println("add <word> <definition>");
        System.out.println("remove <word>");
        System.out.println("print <word>");
        System.out.println("print all");
        System.out.println("exit\n");
    }

    /**
     * Method that reads in a file and adds the words and definitions to the
     * dictionary
     * 
     * @param filename the name of the file
     * @param bst      the binary search tree to enter the words and definitions
     *                 into
     */
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
                String definition = splitData[1].trim();
                bst.insert(key, definition);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
    }

    /**
     * Method that runs a command on the dictionary
     * 
     * @param command the command to run
     * @param bst     the binary search tree dictionary
     */
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
