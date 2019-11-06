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
         reAddOrder();
         throw new OrderError("Ship " + shipID + " already moved!");
      }

      StarSystem current = orderedShip.whereIsShip;
      StarSystem destination = CommonData.galaxy.starSystems.stream().filter(starSystem -> starSystem.ID.equals(destinationID)).findFirst()
              .orElseThrow(() -> new OrderError("System " + destinationID + " not found!"));

      if (current.getConnections().contains(destination)) {
         if (CommonData.fortresses.stream()
                 .filter(fortress -> fortress.whereIsFortress == current)
                 .filter(fortress -> !fortress.owner.equals(orderedShip.owner))
                 .noneMatch(fortress -> fortress.fortifiedLevel > 0)) {
            orderedShip.move(destination);
         } else {
            if (destination == orderedShip.whereWasShip) {
               orderedShip.move(destination);
            } else {
               throw new OrderError("Ship " + shipID + " cannot bypass local fortress!");
            }
         }
      } else {
         throw new OrderError("Systems " + current.ID + " and " + destinationID + " are not connected!");
      }
   }
}
