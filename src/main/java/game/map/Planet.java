package game.map;

import game.player.Player;

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
}
