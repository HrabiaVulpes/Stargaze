package game.player.orders;

import game.CommonData;
import game.map.MapElement;
import game.player.Player;

import java.util.Collections;
import java.util.List;

public abstract class Order {
   public OrderType type;
   public List<MapElement> connectedMapElements;
   Player owner;

   public Order(Player owner, OrderType type) {
      this.owner = owner;
      this.type = type;
      connectedMapElements = Collections.emptyList();
   }

   public abstract void runOrder() throws OrderError;

   public void reAddOrder() {
      CommonData.futureOrders.add(this);
   }
}
