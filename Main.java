public class Main {
   public static void main(String[] args) {
      String temp = "remove Windows donawdmnd";
      // capatalise temp
      String[] split = splitCommand(temp);
      System.err.println(split[0]);
      System.err.println(split[1]);

   }

   public static String[] splitCommand(String command) {
      String[] splitCommand = command.split(" ");
      String method = splitCommand[0];
      command = "";
      for (int i = 1; i < splitCommand.length; i++) {
         command = command + splitCommand[i] + " ";
      }

      if (method.equals("search") || method.equals("remove") || method.equals("print")) {
         String[] result = new String[] { method, command };
         return result;
      } else if (method.equals("add")) {
         splitCommand = command.split(":");
         String key = splitCommand[0].length() > 1 ? splitCommand[0] : "";
         String definition = splitCommand[1].length() > 2 ? splitCommand[1] : "";
         return new String[] { method, key, definition };
      } else {
         return new String[] { method, command };
      }

   }

}