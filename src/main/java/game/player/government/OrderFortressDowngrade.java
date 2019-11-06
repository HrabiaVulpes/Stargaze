package game.player.government;

import game.CommonData;
import game.map.Fortress;
import game.player.Player;

public class OrderFortressDowngrade extends Order {
   private String fortressID;

   public OrderFortressDowngrade(Player owner, String fortressID) {
      super(owner, OrderType.FORTRESS_DOWNGRADE);
      this.fortressID = fortressID;
   }

   @Override
   public void runOrder() throws OrderError {
      Fortress orderedFortress = CommonData.fortresses.stream()
              .filter(fortress -> fortress.owner.equals(owner.name))
              .filter(fortress -> fortress.ID.equals(fortressID))
              .findFirst().orElseThrow(() -> new OrderError("Fortress " + fortressID + " not found!"));

      if (orderedFortress.level > 0) {
         orderedFortress.downgrade();
      } else {
         throw new OrderError("Fortress " + fortressID + " is already at level 0");
      }
   }
}
