package game.player.orders;

public class OrderError extends Exception {
   public OrderError(String message) {
      super("Order Error: " + message);
   }
}
