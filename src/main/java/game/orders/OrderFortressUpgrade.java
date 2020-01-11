package game.orders;

import game.staticData.CommonData;
import game.fortress.Fortress;
import game.player.Player;

import java.util.Collections;

import static game.staticData.CommonData.getFortressByOwnerAndID;

public class OrderFortressUpgrade extends Order {
   private String fortressID;

   public OrderFortressUpgrade(Player owner, String fortressID) {
      super(owner, OrderType.FORTRESS_UPGRADE);
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

      if (owner.money >= orderedFortress.level) {
         owner.money -= orderedFortress.level;
         orderedFortress.upgrade();
      } else {
         throw new OrderError("Insufficient funds to upgrade fortress " + fortressID);
      }
   }
}
