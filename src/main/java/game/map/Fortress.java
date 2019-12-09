package game.map;

import game.CommonData;
import game.player.Player;

import java.awt.*;

public class Fortress extends MapElement {
   public Integer level = 0;
   public Integer fortifiedLevel = 0;
   public StarSystem whereIsFortress;

   public Fortress(Player owner, StarSystem whereIsFortress) {
      super(owner);
      this.whereIsFortress = whereIsFortress;
   }

   public void upgrade() {
      this.level++;
      this.fortifiedLevel++;
   }

   public void downgrade() {
      this.level--;
      this.fortifiedLevel--;
   }

   public void siege() {
      if (CommonData.ships.stream().filter(ship -> ship.whereIsShip != whereIsFortress).anyMatch(ship -> !ship.owner.equals(this.owner))) {
         this.fortifiedLevel--;
      }
   }

   @Override
   public Shape shape(int offset) {
      return new Rectangle(whereIsFortress.getX() + offset, whereIsFortress.getY(), elementSize, elementSize);
   }
}
