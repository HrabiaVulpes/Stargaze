package game.player.government;

import game.player.Player;

public abstract class Order {
   Player owner;
   public OrderType type;

   public Order(Player owner, OrderType type) {
      this.owner = owner;
      this.type = type;
   }

   public abstract void runOrder();
}
