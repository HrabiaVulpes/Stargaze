package game.player.orders;

import game.CommonData;
import game.map.StarSystem;
import game.player.Player;
import game.ship.Ship;

public class OrderMove extends Order {
   private String shipID;
   private String destinationID;

   public OrderMove(Player owner, String shipID, String destinationID) {
      super(owner, OrderType.SHIP_MOVE);
      this.shipID = shipID;
      this.destinationID = destinationID;
   }

   @Override
   public void runOrder() throws OrderError {
      Ship orderedShip = CommonData.ships.stream().filter(ship -> ship.ID.equals(shipID)).findFirst()
              .orElseThrow(() -> new OrderError("Ship " + shipID + " not found!"));

      if (orderedShip.orderedAlready) {
         throw new OrderError("Ship " + shipID + " already moved!");
      }

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
