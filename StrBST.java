public class StrBST {
   private Node root;

   private class Node {
      private String value;
      private Node left, right;
      private int N;

      public Node(String value, int N) {
         this.value = value;
         this.N = N;
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
   private int size(Node x) {
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
   private boolean getBool(Node x, String value) {
      if (x == null) {
         return false;
      }

      int cmp = value.compareTo(x.value);
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
    * @param s the value to insert
    */
   public void insert(String s) {
      root = put(root, s);
   }

   /**
    * method that inserts a value into the BST
    * 
    * @param x     the node to start at
    * @param value the value to insert
    * @return the node that was inserted
    */
   private Node put(Node x, String value) {
      if (x == null)
         return new Node(value, 1);
      int cmp = value.compareTo(x.value);
      if (cmp < 0)
         x.left = put(x.left, value);
      else if (cmp > 0)
         x.right = put(x.right, value);
      else
         x.value = value;
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
   private Node delete(Node x, String value) {
      if (x == null)
         return null;
      int cmp = value.compareTo(x.value);
      if (cmp < 0)
         x.left = delete(x.left, value);
      else if (cmp > 0)
         x.right = delete(x.right, value);
      else {
         if (x.right == null)
            return x.left;
         if (x.left == null)
            return x.right;
         Node t = x;
         x = min(t.right);
         x.right = deleteMin(t.right);
         x.left = t.left;
      }
      x.N = size(x.left) + size(x.right) + 1;
      return x;
   }

   /**
    * method that deletes the minimum value in the BST
    * 
    * @param x the node to start at
    * @return the node that was deleted
    */
   private Node deleteMin(Node x) {
      if (x.left == null)
         return x.right;
      x.left = deleteMin(x.left);
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
   private Node min(Node x) {
      if (x.left == null)
         return x;
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
   private Node max(Node x) {
      if (x.right == null)
         return x;
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
   private void print(Node x) {
      if (x == null)
         return;
      print(x.left);
      System.out.println(x.value + "| left: " + (x.left == null ? "null" : x.left.value) + "\t| right: "
            + (x.right == null ? "null" : x.right.value) + "\t| N: " + x.N);
      print(x.right);
   }
}
