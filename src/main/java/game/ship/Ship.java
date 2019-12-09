package game.ship;

import game.Utils;
import game.map.MapElement;
import game.map.StarSystem;
import game.player.Player;

import java.awt.*;

public class Ship extends MapElement {
   public ShipTypes shipType;
   public Integer defences = 0;
   public Integer offences = 0;
   public Integer hullPoints = 1;
   public StarSystem whereIsShip;
   public StarSystem whereWasShip;

   public Ship(Player owner, ShipTypes shipType, StarSystem whereIsShip) {
      super(owner);
      this.shipType = shipType;
      defences = owner.defencesTech;
      offences = owner.offencesTech;
      this.whereIsShip = whereIsShip;
      this.whereWasShip = whereIsShip;
   }

   public Integer shoot() {
      return Utils.numberBetween(0, shipType.dice) + offences;
   }

   public Integer defend() {
      return Utils.numberBetween(0, shipType.dice) + defences;
   }

   public void move(StarSystem destination) {
      whereWasShip = whereIsShip;
      whereIsShip = destination;
   }

   @Override
   public Shape shape(int offset) {
      Polygon polygon = new Polygon();
      polygon.xpoints = new int[]{whereIsShip.getX() + elementSize / 2 + offset, whereIsShip.getX() + elementSize + offset, whereIsShip.getX() + offset};
      polygon.ypoints = new int[]{whereIsShip.getY() - elementSize, whereIsShip.getY(), whereIsShip.getY()};
      polygon.npoints = 3;
      return polygon;
   }
}
