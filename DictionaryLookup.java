import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;

public class DictionaryLookup {
    public static void main(String[] args) {
        DictionaryBST bst = new DictionaryBST();
        readInFile("CS-Dictionary-full.txt", bst);
        bst.printDictionaryItem("Memory");
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
}
