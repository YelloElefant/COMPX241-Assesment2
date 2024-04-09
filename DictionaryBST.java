public class DictionaryBST {
   private DictionaryNode root;

   private class DictionaryNode {
      private String value;
      private DictionaryNode left, right;
      private int N;
      public String definition;

      public DictionaryNode(String value, int N, String definition) {
         this.value = value;
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
   public boolean search(String value) {
      return getBool(root, value);
   }

   private boolean getBool(DictionaryNode x, String value) {
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

   public void insert(String s, String definition) {
      root = put(root, s, definition);
   }

   private DictionaryNode put(DictionaryNode x, String value, String definition) {
      if (x == null)
         return new DictionaryNode(value, 1, definition);
      int cmp = value.compareTo(x.value);
      if (cmp < 0)
         x.left = put(x.left, value, definition);
      else if (cmp > 0)
         x.right = put(x.right, value, definition);
      else
         x.value = value;
      x.N = 1 + size(x.left) + size(x.right);
      return x;
   }

   public void remove(String value) {
      root = delete(root, value);
   }

   // deletes a specific DictionaryNode and moves the left most of the right
   // sub-tree to the
   // deleted DictionaryNode
   private DictionaryNode delete(DictionaryNode x, String value) {
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
      return min(root).value;
   }

   private DictionaryNode min(DictionaryNode x) {
      if (x.left == null)
         return x;
      return min(x.left);
   }

   // gets the right most of a tree
   public String max() {
      return max(root).value;
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
      System.out.println(x.value + "| left: " + (x.left == null ? "null" : x.left.value) + "\t| right: "
            + (x.right == null ? "null" : x.right.value) + "\t| N: " + x.N);
      print(x.right);
   }

   // method that prints a specific DictionaryNode
   public void printDictionaryItem(String value) {
      printDictionaryItem(root, value);
   }

   private void printDictionaryItem(DictionaryNode x, String value) {
      if (x == null)
         return;
      int cmp = value.compareTo(x.value);
      if (cmp < 0)
         printDictionaryItem(x.left, value);
      else if (cmp > 0)
         printDictionaryItem(x.right, value);
      else
         System.out.println(x.value + " : " + x.definition);
   }
}
