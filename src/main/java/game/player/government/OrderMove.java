package game.player.government;

import game.CommonData;
import game.map.StarSystem;
import game.player.Player;
import game.ship.Ship;

public class OrderMove extends Order {
   private String shipId;
   private String destinationID;

   public OrderMove(Player owner, String shipId, String destinationID) {
      super(owner, OrderType.SHIP_MOVE);
      this.shipId = shipId;
      this.destinationID = destinationID;
   }

   @Override
   public void runOrder() throws OrderError {
      Ship orderedShip = CommonData.ships.stream().filter(ship -> ship.ID.equals(shipId)).findFirst()
              .orElseThrow(() -> new OrderError("Ship " + shipId + " not found!"));
      StarSystem current = orderedShip.whereIsShip;
      StarSystem destination = CommonData.galaxy.stream().filter(starSystem -> starSystem.ID.equals(destinationID)).findFirst()
              .orElseThrow(() -> new OrderError("Ship " + destinationID + " not found!"));
      if (current.getConnections().contains(destination)) {
         orderedShip.move(destination);
      } else {
         throw new OrderError("Systems " + current.ID + " and " + destinationID + " are not connected!");
      }
   }
}
