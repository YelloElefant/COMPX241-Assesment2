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

        // print all the commands
        printCommandList();

        System.out.println("Loading Dictionary...");

        // create a new dictionary
        DictionaryBST bst = new DictionaryBST();

        // read in the dictionary file
        readInFile("CS-Dictionary-full.txt", bst);
        System.out.println("Dictionary Loaded: " + bst.size() + " words\n");

        // run the command line interface scanner
        Scanner scanner = new Scanner(System.in);

        // run the command line interface
        while (true) {
            // get the command from the user
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();

            // check if the user wants to exit
            if (command.equals("exit")) {
                break;
            }

            // run the command
            runCommand(command, bst, scanner);
        }
        scanner.close();
    }

    /**
     * Method that prints the command list
     */
    public static void printCommandList() {
        System.out.println("Commands: ");
        System.out.println("search <word>");
        System.out.println("add <word> : <definition>");
        System.out.println("remove <word>");
        System.out.println("print <word>");
        System.out.println("print all");
        System.out.println("help");
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
            // read in the file
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);

            // read in the words and definitions
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                // skip empty lines
                if (data.isEmpty()) {
                    continue;
                }

                // split the data into the word and definition
                String[] splitData = data.split(":");
                String key = splitData[0];
                String definition = splitData[1].trim();

                // add the word and definition to the dictionary
                bst.insert(key, definition);
            }

            // close the file reader
            myReader.close();
        } catch (FileNotFoundException e) {
            // print the error message
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
    public static void runCommand(String command, DictionaryBST bst, Scanner scanner) {
        // split the command into the method, key, and definition
        String[] splitCommand = splitCommand(command);
        String method = splitCommand[0];
        String key = splitCommand.length > 1 ? splitCommand[1] : "";
        String definition = splitCommand.length > 2 ? splitCommand[2] : "";

        try {
            // run the command
            if (method.equals("search")) {
                // search for the word in the dictionary
                boolean result = bst.search(key);
                System.out.print("Dictionary ");
                System.out.println(result ? "Does Contain: " + key : "Doesn't Contain: " + key);

            } else if (method.equals("add")) {
                // if the definition is blank throw an exception
                if (definition.isBlank()) {
                    throw new Exception("Could not add \"" + key + "\" Definition cannot be blank");
                }
                // add the word and definition to the dictionary
                bst.insert(key, definition);
                System.out.println("Added: \"" + key + "\" to dictionary");

            } else if (method.equals("remove")) {
                // remove the word from the dictionary
                bst.remove(key);

            } else if (method.equals("print")) {
                // print the word and definition from the dictionary
                if (key.equals("all")) {
                    bst.printDictionary();
                } else {
                    bst.printDictionaryItem(key);
                }

            } else if (method.equals("help")) {
                // print the command list
                printCommandList();

            } else {
                // throw an exception if the command is invalid/empty
                throw new Exception("Invalid Command\n");

            }
        } catch (Exception ex) {
            // print the error message
            System.err.println(ex.getMessage());
        }
    }

    public static String[] splitCommand(String command) {
        String[] splitCommand = command.split(" ");
        String method = splitCommand[0];
        command = "";
        for (int i = 1; i < splitCommand.length; i++) {
            command = command + splitCommand[i] + " ";
        }
        command = command.trim();

        if (method.equals("add")) {
            splitCommand = command.split(":");
            String key = (splitCommand[0].length() > 1 ? splitCommand[0] : "").trim();
            String definition = (splitCommand[1].length() > 2 ? splitCommand[1] : "").trim();
            return new String[] { method, key, definition };
        }
        return new String[] { method, command };
    }
}
