package game;

public class Utils {
   private static long id = 0;
   public static int numberBetween(int a, int b) {
      return (int) (Math.random() * (b - a) + a);
   }
   public static long nextID(){
      id++;
      return id;
   }
}
