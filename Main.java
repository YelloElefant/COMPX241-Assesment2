public class Main {
   public static void main(String[] args) {
      String temp = "add  L      L";
      System.out.println(temp.trim());
      System.out.println(temp.trim().replaceAll(" +", " "));

   }
}