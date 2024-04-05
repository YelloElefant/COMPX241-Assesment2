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

   public void removeMin() {
      root = deleteMin(root);
   }

   private Node deleteMin(Node x) {
      if (x.left == null)
         return x.right;
      x.left = deleteMin(x.left);
      x.N = size(x.left) + size(x.right) + 1;
      return x;
   }

   public String min() {
      return min(root).key;
   }

   private Node min(Node x) {
      if (x.left == null)
         return x;
      return min(x.left);
   }

   public String max() {
      return max(root).key;
   }

   private Node max(Node x) {
      if (x.right == null)
         return x;
      return max(x.right);
   }

   public String floor(String key) {
      Node x = floor(root, key);
      if (x == null)
         return null;
      return x.key;
   }

   private Node floor(Node x, String key) {
      if (x == null)
         return null;
      int cmp = key.compareTo(x.key);
      if (cmp == 0)
         return x;
      if (cmp < 0)
         return floor(x.left, key);
      Node t = floor(x.right, key);
      if (t != null)
         return t;
      else
         return x;
   }

   public String ceiling(String key) {
      Node x = ceiling(root, key);
      if (x == null)
         return null;
      return x.key;
   }

   private Node ceiling(Node x, String key) {
      if (x == null)
         return null;
      int cmp = key.compareTo(x.key);
      if (cmp == 0)
         return x;
      if (cmp > 0)
         return ceiling(x.right, key);
      Node t = ceiling(x.left, key);
      if (t != null)
         return t;
      else
         return x;
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
            + (x.right == null ? "null" : x.right.key));
      print(x.right);
   }
}
