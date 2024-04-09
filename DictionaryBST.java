public class DictionaryBST {
   private DictionaryNode root;

   private class DictionaryNode {
      private String key;
      private DictionaryNode left, right;
      private int N;
      public String definition;

      public DictionaryNode(String key, int N, String definition) {
         this.key = key;
         this.N = N;
         this.definition = definition;
      }
   }

   public int size() {
      return size(root);
   }

   private int size(DictionaryNode x) {
      if (x == null)
         return 0;
      else
         return x.N;
   }

   // make a bool search method
   public boolean search(String key) {
      return getBool(root, key);
   }

   private boolean getBool(DictionaryNode x, String key) {
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

   public void insert(String s, String definition) {
      root = put(root, s, definition);
   }

   private DictionaryNode put(DictionaryNode x, String key, String definition) {
      if (x == null)
         return new DictionaryNode(key, 1, definition);
      int cmp = key.compareTo(x.key);
      if (cmp < 0)
         x.left = put(x.left, key, definition);
      else if (cmp > 0)
         x.right = put(x.right, key, definition);
      else
         x.key = key;
      x.N = 1 + size(x.left) + size(x.right);
      return x;
   }

   public void remove(String key) {
      root = delete(root, key);
   }

   // deletes a specific DictionaryNode and moves the left most of the right
   // sub-tree to the
   // deleted DictionaryNode
   private DictionaryNode delete(DictionaryNode x, String key) {
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
         DictionaryNode t = x;
         x = min(t.right);
         x.right = deleteMin(t.right);
         x.left = t.left;
      }
      x.N = size(x.left) + size(x.right) + 1;
      return x;
   }

   // deletes the left most of a tree
   private DictionaryNode deleteMin(DictionaryNode x) {
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

   private DictionaryNode min(DictionaryNode x) {
      if (x.left == null)
         return x;
      return min(x.left);
   }

   // gets the right most of a tree
   public String max() {
      return max(root).key;
   }

   private DictionaryNode max(DictionaryNode x) {
      if (x.right == null)
         return x;
      return max(x.right);
   }

   // make in-order traversal method for print
   public void print() {
      print(root);
   }

   private void print(DictionaryNode x) {
      if (x == null)
         return;
      print(x.left);
      System.out.println(x.key + "| left: " + (x.left == null ? "null" : x.left.key) + "\t| right: "
            + (x.right == null ? "null" : x.right.key) + "\t| N: " + x.N);
      print(x.right);
   }
}
