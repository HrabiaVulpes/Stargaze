package game;

public class Utils {
   public static int numberBetween(int a, int b) {
      return (int) (Math.random() * (b - a) + a);
   }
}
