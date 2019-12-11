package game.ship;

import org.omg.CORBA.PUBLIC_MEMBER;

public enum ShipTypes {
   CORVETTE(1, 4),
   FRIGATE(2, 6),
   DESTROYER(3, 8),
   CRUISER(4, 10),
   BATTLESHIP(5, 12);

   Integer size;
   Integer dice;

   ShipTypes(Integer size, Integer dice) {
      this.size = size;
      this.dice = dice;
   }

   public Integer getSize() {
      return size;
   }

   public Integer getDice() {
      return dice;
   }

   public static ShipTypes bySize(int size){
      switch (size){
         case 1:
            return CORVETTE;
         case 2:
            return FRIGATE;
         case 3:
            return DESTROYER;
         case 4:
            return CRUISER;
         case 5:
            return BATTLESHIP;
      }
      return CORVETTE;
   }
}
