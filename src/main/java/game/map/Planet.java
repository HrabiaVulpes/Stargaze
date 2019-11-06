package game.map;

public class Planet extends MapElement{
   public Integer level = 1;

   public Planet(String owner) {
      super(owner);
   }

   public void upgrade(){
      level++;
   }

   public void downgrade(){
      level--;
   }
}
