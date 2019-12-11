package game.player.orders;

import game.CommonData;
import game.map.Fortress;
import game.player.Player;

import java.util.Collections;

public class OrderFortressUpgrade extends Order {
   private String fortressID;

   public OrderFortressUpgrade(Player owner, String fortressID) {
      super(owner, OrderType.FORTRESS_UPGRADE);
      this.fortressID = fortressID;
      connectedMapElements = Collections.singletonList(CommonData.fortresses.stream().filter(fortress -> fortress.ID.equals(fortressID)).findFirst().orElse(null));
   }

   @Override
   public void runOrder() throws OrderError {
      Fortress orderedfortress = CommonData.fortresses.stream()
              .filter(fortress -> fortress.owner.equals(owner.name))
              .filter(fortress -> fortress.ID.equals(fortressID))
              .findFirst().orElseThrow(() -> new OrderError("Fortress " + fortressID + " not found!"));

      if (orderedfortress.orderedAlready) {
         reAddOrder();
         throw new OrderError("Fortress " + fortressID + " already moved!");
      }

      if (owner.money >= orderedfortress.level) {
         owner.money -= orderedfortress.level;
         orderedfortress.upgrade();
      } else {
         throw new OrderError("Insufficient funds to upgrade fortress " + fortressID);
      }
   }
}
