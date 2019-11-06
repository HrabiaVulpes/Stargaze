package game.player.government;

import game.CommonData;
import game.map.Fortress;
import game.player.Player;

import java.util.NoSuchElementException;

public class OrderFortressUpgrade extends Order {
   private String fortressID;

   public OrderFortressUpgrade(Player owner, String fortressID) {
      super(owner, OrderType.FORTRESS_UPGRADE);
      this.fortressID = fortressID;
   }

   @Override
   public void runOrder() {
      Fortress orderedfortress = CommonData.fortresses.stream()
              .filter(fortress -> fortress.owner.equals(owner.name))
              .filter(fortress -> fortress.ID.equals(fortressID))
              .findFirst().orElseThrow(NoSuchElementException::new);

      if (owner.money >= orderedfortress.level){
         owner.money -= orderedfortress.level;
         orderedfortress.upgrade();
      }
   }
}
