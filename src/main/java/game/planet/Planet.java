package game.planet;

import game.map.MapElement;
import game.map.StarSystem;
import game.player.Player;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Planet extends MapElement {
   public Integer level = 1;
   public StarSystem whereIsPlanet;

   public Planet(Player owner, StarSystem system) {
      super(owner);
      this.whereIsPlanet = system;
   }

   public void upgrade() {
      level++;
   }

   public void downgrade() {
      level--;
   }

   @Override
   public Shape shape(int offset) {
      return new Ellipse2D.Double(whereIsPlanet.getX() - elementSize - offset, whereIsPlanet.getY() - elementSize, elementSize, elementSize);
   }
}
