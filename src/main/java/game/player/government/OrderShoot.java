package game.player.government;

import game.CommonData;
import game.player.Player;
import game.ship.Ship;

public class OrderShoot extends Order {
   private String attackerID;
   private String defenderID;

   public OrderShoot(Player owner, String attackerID, String defenderID) {
      super(owner, OrderType.SHIP_SHOOT);
      this.attackerID = attackerID;
      this.defenderID = defenderID;
   }

   @Override
   public void runOrder() throws OrderError {
      Ship attacker = CommonData.ships.stream()
              .filter(ship -> ship.owner.equals(owner.name))
              .filter(ship -> ship.ID.equals(attackerID))
              .findFirst().orElseThrow(() -> new OrderError("Ship " + attackerID + " not found!"));

      Ship defender = CommonData.ships.stream()
              .filter(ship -> ship.ID.equals(defenderID))
              .findFirst().orElseThrow(() -> new OrderError("Ship " + defenderID + " not found!"));

      if (attacker.whereIsShip.ID.equals(defender.whereIsShip.ID)) {
         int attack = attacker.shoot();
         int defend = defender.defend();

         if (attacker.shipType.getSize() <= defender.shipType.getSize()) {
            if (attack > defend) {
               CommonData.ships.remove(defender);
            }
         } else {
            if (attack < defend) {
               CommonData.ships.remove(defender);
            }
         }
      } else {
         throw new OrderError("Ships " + attackerID + " and " + defenderID + " are not in the same system!");
      }
   }
}
