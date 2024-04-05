public class StrBST {
   private Node root;

   private class Node {
      private String key;
      private Node left, right;
      private int N;

      public Node(String key, int N) {
         this.key = key;
         this.N = N;
      }
   }

   public int size() {
      return size(root);
   }

   private int size(Node x) {
      if (x == null)
         return 0;
      else
         return x.N;
   }

   // make a bool search method
   public boolean search(String key) {
      return getBool(root, key);
   }

   private boolean getBool(Node x, String key) {
      if (x == null) {
         return false;
      }

      int cmp = key.compareTo(x.key);
      if (cmp < 0)
         return getBool(x.left, key);
      else if (cmp > 0)
         return getBool(x.right, key);
      else
         return true;
   }

   public void insert(String s) {
      root = put(root, s);
   }

   private Node put(Node x, String key) {
      if (x == null)
         return new Node(key, 1);
      int cmp = key.compareTo(x.key);
      if (cmp < 0)
         x.left = put(x.left, key);
      else if (cmp > 0)
         x.right = put(x.right, key);
      else
         x.key = key;
      x.N = 1 + size(x.left) + size(x.right);
      return x;
   }

   public void remove(String key) {
      root = delete(root, key);
   }

   // deletes a specific node and moves the left most of the right sub-tree to the
   // deleted node
   private Node delete(Node x, String key) {
      if (x == null)
         return null;
      int cmp = key.compareTo(x.key);
      if (cmp < 0)
         x.left = delete(x.left, key);
      else if (cmp > 0)
         x.right = delete(x.right, key);
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

   // deletes the left most of a tree
   private Node deleteMin(Node x) {
      if (x.left == null)
         return x.right;
      x.left = deleteMin(x.left);
      x.N = size(x.left) + size(x.right) + 1;
      return x;
   }

   // gets the left most of a tree
   public String min() {
      return min(root).key;
   }

   private Node min(Node x) {
      if (x.left == null)
         return x;
      return min(x.left);
   }

   // gets the right most of a tree
   public String max() {
      return max(root).key;
   }

   private Node max(Node x) {
      if (x.right == null)
         return x;
      return max(x.right);
   }

   // make in-order traversal method for print
   public void print() {
      print(root);
   }

   private void print(Node x) {
      if (x == null)
         return;
      print(x.left);
      System.out.println(x.key + "| left: " + (x.left == null ? "null" : x.left.key) + "\t| right: "
            + (x.right == null ? "null" : x.right.key) + "\t| N: " + x.N);
      print(x.right);
   }
}
