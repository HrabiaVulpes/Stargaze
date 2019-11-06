package game.player.government;

public class OrderError extends Exception {
   public OrderError(String message) {
      super("Order Error: " + message);
   }
}
