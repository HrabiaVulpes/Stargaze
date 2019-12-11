package game.player.orders;

import game.CommonData;
import game.map.Fortress;
import game.player.Player;

import java.util.Collections;

public class OrderFortressDowngrade extends Order {
   private String fortressID;

   public OrderFortressDowngrade(Player owner, String fortressID) {
      super(owner, OrderType.FORTRESS_DOWNGRADE);
      this.fortressID = fortressID;
      connectedMapElements = Collections.singletonList(CommonData.fortresses.stream().filter(fortress -> fortress.ID.equals(fortressID)).findFirst().orElse(null));
   }

   @Override
   public void runOrder() throws OrderError {
      Fortress orderedFortress = CommonData.fortresses.stream()
              .filter(fortress -> fortress.owner == owner)
              .filter(fortress -> fortress.ID.equals(fortressID))
              .findFirst().orElseThrow(() -> new OrderError("Fortress " + fortressID + " not found!"));

      if (orderedFortress.orderedAlready) {
         reAddOrder();
         throw new OrderError("Fortress " + fortressID + " already moved!");
      }

      if (orderedFortress.level > 0) {
         orderedFortress.downgrade();
         orderedFortress.orderedAlready = true;
      } else {
         throw new OrderError("Fortress " + fortressID + " is already at level 0");
      }
   }
}
