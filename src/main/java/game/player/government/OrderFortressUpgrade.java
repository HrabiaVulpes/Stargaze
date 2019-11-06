package game.player.government;

import game.CommonData;
import game.map.Fortress;
import game.player.Player;

public class OrderFortressUpgrade extends Order {
   private String fortressID;

   public OrderFortressUpgrade(Player owner, String fortressID) {
      super(owner, OrderType.FORTRESS_UPGRADE);
      this.fortressID = fortressID;
   }

   @Override
   public void runOrder() throws OrderError {
      Fortress orderedfortress = CommonData.fortresses.stream()
              .filter(fortress -> fortress.owner.equals(owner.name))
              .filter(fortress -> fortress.ID.equals(fortressID))
              .findFirst().orElseThrow(() -> new OrderError("Fortress " + fortressID + " not found!"));

      if (owner.money >= orderedfortress.level) {
         owner.money -= orderedfortress.level;
         orderedfortress.upgrade();
      } else {
         throw new OrderError("Insufficient funds to upgrade fortress " + fortressID);
      }
   }
}
