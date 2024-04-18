public class Main {
   public static void main(String[] args) {
      String temp = "hello";
      // capatalise temp
      temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
      System.out.println(temp);

   }
}