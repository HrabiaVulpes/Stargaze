package game.player.government;

import game.CommonData;
import game.map.Fortress;
import game.player.Player;

import java.util.NoSuchElementException;

public class OrderFortressDowngrade extends Order {
   private String fortressID;

   public OrderFortressDowngrade(Player owner, String fortressID) {
      super(owner, OrderType.FORTRESS_DOWNGRADE);
      this.fortressID = fortressID;
   }

   @Override
   public void runOrder() {
      Fortress orderedFortress = CommonData.fortresses.stream()
              .filter(fortress -> fortress.owner.equals(owner.name))
              .filter(fortress -> fortress.ID.equals(fortressID))
              .findFirst().orElseThrow(NoSuchElementException::new);

      if (orderedFortress.level > 1) {
         orderedFortress.downgrade();
      }
   }
}
