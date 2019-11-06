package game.ship;

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
}
