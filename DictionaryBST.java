/**
 * DictionaryBST.java
 * 
 * This class is a binary search tree implementation of a dictionary. It has
 * methods to insert, remove, search, print, and print all the nodes in the
 * dictionary. It also has methods to print a specific node and its definition
 * and to print the minimum and maximum nodes in the dictionary.
 * 
 * The DictionaryNode class is a private inner class that holds the value of the
 * node, the left and right children, the size of the node, and the definition
 * of
 * the node.
 * 
 * @author Alexander Trotter
 * @version 04/15/2021
 * 
 * 
 * 
 */
public class DictionaryBST {
   /**
    * The root of the binary search tree
    */
   private DictionaryNode root;

   /**
    * DictionaryBSTNode class
    */
   private class DictionaryNode {
      // The value of the node
      private String value;
      // The left and right children of the node
      private DictionaryNode left, right;
      // The size of the node
      private int N;
      // The definition of the node
      public String definition;

      /**
       * Constructor for the DictionaryNode class
       * 
       * @param value      the value of the node
       * @param N          the size of the node
       * @param definition the definition of the node
       */
      public DictionaryNode(String value, int N, String definition) {
         this.value = value;
         this.N = N;
         this.definition = definition;
      }
   }

   /**
    * method that gets the size of the BST recursivaly
    * 
    * @return the size of the BST
    */
   public int size() {
      return size(root);
   }

   /**
    * method that gets the size of a specific node
    * 
    * @param x
    * @return the size of the node
    */
   private int size(DictionaryNode x) {
      if (x == null)
         return 0;
      else
         return x.N;
   }

   /**
    * method that searches for a specific value in the BST
    * 
    * @param value the value to search for
    * @return true if the value is in the BST, false if it is not
    */
   public boolean search(String value) {
      return getBool(root, value);
   }

   /**
    * method that gets a specific value in the BST
    * 
    * @param x     the node to start at
    * @param value the value to search for
    * @return true if the value is in the BST, false if it is not
    */
   private boolean getBool(DictionaryNode x, String value) {
      // if the node is null, return false
      if (x == null) {
         return false;
      }

      // compare the value to the current node
      int cmp = value.compareTo(x.value);

      // if the value is less than the current node, search the left child, if the
      // value is greater than the current node, search the right child,
      // if the value is equal to the current node, return true
      if (cmp < 0)
         return getBool(x.left, value);
      else if (cmp > 0)
         return getBool(x.right, value);
      else
         return true;
   }

   /**
    * method that inserts a value into the BST
    * 
    * @param s          the value to insert
    * @param definition the definition of the value
    */
   public void insert(String s, String definition) {
      root = put(root, s, definition);
   }

   /**
    * method that inserts a value into the BST
    * 
    * @param x          the node to start at
    * @param value      the value to insert
    * @param definition the definition of the value
    * @return the node that was inserted
    */
   private DictionaryNode put(DictionaryNode x, String value, String definition) {
      // if the node is null, create a new node
      if (x == null)
         return new DictionaryNode(value, 1, definition);

      // compare the value to the current node
      int cmp = value.compareTo(x.value);

      // if the value is less than the current node, insert the value into the left
      // child, if the value is greater than the current node, insert the value into
      // the right child,
      // if the value is equal to the current node, update the definition of the
      // current node
      if (cmp < 0)
         x.left = put(x.left, value, definition);
      else if (cmp > 0)
         x.right = put(x.right, value, definition);
      else
         x.value = value;

      // update the size of the current node
      x.N = 1 + size(x.left) + size(x.right);
      return x;
   }

   /**
    * method that removes a value from the BST
    * 
    * @param value the value to remove
    */
   public void remove(String value) {
      root = delete(root, value);
   }

   /**
    * method that removes a value from the BST
    * 
    * @param x     the node to start at
    * @param value the value to remove
    * @return the node that was removed
    */
   private DictionaryNode delete(DictionaryNode x, String value) {
      // if the node is null, return null
      if (x == null)
         return null;

      // compare the value to the current node
      int cmp = value.compareTo(x.value);

      // if the value is less than the current node, remove the value from the left
      // child, if the value is greater than the current node, remove the value from
      // the right child,
      // if the value is equal to the current node, remove the current node and
      // replace it with the minimum value in the right child
      if (cmp < 0)
         x.left = delete(x.left, value);
      else if (cmp > 0)
         x.right = delete(x.right, value);
      else {
         if (x.right == null)
            return x.left;
         if (x.left == null)
            return x.right;
         DictionaryNode t = x;
         x = min(t.right);
         x.right = deleteMin(t.right);
         x.left = t.left;
      }

      // update the size of the current node
      x.N = size(x.left) + size(x.right) + 1;
      return x;
   }

   /**
    * method that deletes the minimum value in the BST
    * 
    * @param x the node to start at
    * @return the node that was deleted
    */
   private DictionaryNode deleteMin(DictionaryNode x) {
      // if the left child is null, return the right child
      if (x.left == null)
         return x.right;

      // remove the minimum value from the left child
      x.left = deleteMin(x.left);

      // update the size of the current node
      x.N = size(x.left) + size(x.right) + 1;

      return x;
   }

   /**
    * method that gets the minimum value in the BST (left most node)
    * 
    * @return the minimum value in the BST
    */
   public String min() {
      return min(root).value;
   }

   /**
    * method that gets the minimum value in the BST (left most node)
    * 
    * @param x the node to start at
    * @return the minimum value in the BST
    */
   private DictionaryNode min(DictionaryNode x) {
      // if the left child is null, return the current node
      if (x.left == null)
         return x;

      // get the minimum value from the left child
      return min(x.left);
   }

   /**
    * method that gets the maximum value in the BST (right most node)
    * 
    * @return the maximum value in the BST
    */
   public String max() {
      return max(root).value;
   }

   /**
    * method that gets the maximum value in the BST (right most node)
    * 
    * @param x the node to start at
    * @return the maximum value in the BST
    */
   private DictionaryNode max(DictionaryNode x) {
      // if the right child is null, return the current node
      if (x.right == null)
         return x;

      // get the maximum value from the right child
      return max(x.right);
   }

   /**
    * method that prints the BST
    */
   public void print() {
      print(root);
   }

   /**
    * method that prints the BST
    * 
    * @param x the node to start at
    */
   private void print(DictionaryNode x) {
      // if the node is null, return
      if (x == null)
         return;

      // print the left child, the current node, and the right child
      print(x.left);

      // print the current node
      System.out.println(x.value + "| left: " + (x.left == null ? "null" : x.left.value) + "\t| right: "
            + (x.right == null ? "null" : x.right.value) + "\t| N: " + x.N);

      // print the right child
      print(x.right);
   }

   /**
    * method that prints a specific value and its definition
    * 
    * @param value the value to print
    * @throws Exception if the value is not in the BST
    */
   public void printDictionaryItem(String value) throws Exception {
      // if the value is in the BST, print the value and its definition
      if (search(value)) {
         printDictionaryItem(root, value);
      } else {
         // if the value is not in the BST, throw an exception
         throw new Exception(("The word \"" + value + "\" is not in the dictionary\n"));
      }
   }

   /**
    * method that prints a specific value and its definition
    * 
    * @param x     node to start at
    * @param value the value to print and stop searching at
    */
   private void printDictionaryItem(DictionaryNode x, String value) {
      // if the node is null, return
      if (x == null)
         return;

      // compare the value to the current node
      int cmp = value.compareTo(x.value);

      // if the value is less than the current node, search the left child, if the
      // value is greater than the current node, search the right child,
      // if the value is equal to the current node, print the value and its definition
      if (cmp < 0)
         printDictionaryItem(x.left, value);
      else if (cmp > 0)
         printDictionaryItem(x.right, value);
      else
         System.out.println(x.value + " : " + x.definition);
   }

   /**
    * method that prints all the nodes in the BST
    */
   public void printDictionary() {
      printDictionary(root);
      System.out.println("");
   }

   /**
    * method that prints all the nodes in the BST
    * 
    * @param x the node to start at
    */
   private void printDictionary(DictionaryNode x) {
      // if the node is null, return
      if (x == null)
         return;

      // print the left child, the current node, and the right child
      printDictionary(x.left);
      System.out.println(x.value + "\n" + x.definition + "\n");
      printDictionary(x.right);
   }
}
