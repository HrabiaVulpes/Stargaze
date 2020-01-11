package game.orders;

import game.staticData.CommonData;
import game.fortress.Fortress;
import game.player.Player;

import java.util.Collections;

import static game.staticData.CommonData.getFortressByOwnerAndID;

public class OrderFortressDowngrade extends Order {
   private String fortressID;

   public OrderFortressDowngrade(Player owner, String fortressID) {
      super(owner, OrderType.FORTRESS_DOWNGRADE);
      this.fortressID = fortressID;
      connectedMapElements = Collections.singletonList(CommonData.fortresses.stream().filter(fortress -> fortress.ID.equals(fortressID)).findFirst().orElse(null));
   }

   @Override
   public void runOrder() throws OrderError {
      Fortress orderedFortress = getFortressByOwnerAndID(owner, fortressID);

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
