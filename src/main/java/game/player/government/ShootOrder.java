package game.player.government;

import game.CommonData;
import game.player.Player;
import game.ship.Ship;

import java.util.NoSuchElementException;

public class ShootOrder extends Order {
   private String attackerID;
   private String defenderID;

   public ShootOrder(Player owner, String attackerID, String defenderID) {
      super(owner, OrderType.SHIP_SHOOT);
      this.attackerID = attackerID;
      this.defenderID = defenderID;
   }

   @Override
   public void runOrder() {
      Ship attacker = CommonData.ships.stream()
              .filter(ship -> ship.owner.equals(owner.name))
              .filter(ship -> ship.ID.equals(attackerID))
              .findFirst().orElseThrow(NoSuchElementException::new);

      Ship defender = CommonData.ships.stream()
              .filter(ship -> ship.ID.equals(defenderID))
              .findFirst().orElseThrow(NoSuchElementException::new);

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
      }
   }
}
