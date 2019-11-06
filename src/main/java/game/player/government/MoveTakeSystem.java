package game.player.government;

import game.CommonData;
import game.map.Fortress;
import game.map.StarSystem;
import game.player.Player;
import game.ship.Ship;

import java.util.NoSuchElementException;

public class MoveTakeSystem extends Order {
   private String shipID;

   public MoveTakeSystem(Player owner, String shipID) {
      super(owner, OrderType.SHIP_TAKE_SYSTEM);
      this.shipID = shipID;
   }

   @Override
   public void runOrder() {
      Ship orderedShip = CommonData.ships.stream()
              .filter(ship -> ship.owner.equals(owner.name))
              .filter(ship -> ship.ID.equals(shipID))
              .findFirst().orElseThrow(NoSuchElementException::new);

      StarSystem system = orderedShip.whereIsShip;
      Fortress systemFortress = CommonData.fortresses.stream()
              .filter(fortress -> fortress.whereIsFortress == system)
              .findFirst().orElse(new Fortress("none", system));

      if (systemFortress.fortifiedLevel <= 0) {
         system.setOwner(orderedShip.owner);
      }
   }
}
