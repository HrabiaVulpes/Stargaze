package game.ship;

import game.Utils;
import game.map.MapElement;
import game.map.StarSystem;
import game.player.Player;

public class Ship extends MapElement {
   public ShipTypes shipType;
   public Integer defences = 0;
   public Integer offences = 0;
   public Integer hullPoints = 1;
   public StarSystem whereIsShip;

   public Ship(Player owner, ShipTypes shipType, StarSystem whereIsShip) {
      super(owner.name);
      this.shipType = shipType;
      defences = owner.defencesTech;
      offences = owner.offencesTech;
      this.whereIsShip = whereIsShip;
   }

   public Integer shoot() {
      return Utils.numberBetween(0, shipType.dice) + offences;
   }

   public Integer defend() {
      return Utils.numberBetween(0, shipType.dice) + defences;
   }

   public void move(StarSystem destination) {
      whereIsShip = destination;
   }
}
