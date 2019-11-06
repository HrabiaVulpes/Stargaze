package game.player.government;

import game.CommonData;
import game.map.StarSystem;
import game.player.Player;
import game.ship.Ship;

import java.util.NoSuchElementException;

public class MoveOrder extends Order {
   private String shipId;
   private String destinationID;

   public MoveOrder(Player owner, String shipId, String destinationID) {
      super(owner, OrderType.SHIP_MOVE);
      this.shipId = shipId;
      this.destinationID = destinationID;
   }

   @Override
   public void runOrder() {
      Ship orderedShip = CommonData.ships.stream().filter(ship -> ship.ID.equals(shipId)).findFirst().orElseThrow(NoSuchElementException::new);
      StarSystem current = orderedShip.whereIsShip;
      StarSystem destination = CommonData.galaxy.stream().filter(starSystem -> starSystem.ID.equals(destinationID)).findFirst().orElseThrow(NoSuchElementException::new);
      if (current.getConnections().contains(destination)) {
         orderedShip.move(destination);
      }
   }
}
