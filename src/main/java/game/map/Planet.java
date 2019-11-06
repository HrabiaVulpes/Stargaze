package game.map;

public class Planet extends MapElement{
   public Integer level = 1;
   public StarSystem whereIsPlanet;
   public boolean shipyardUsed = false;

   public Planet(String owner, StarSystem system) {
      super(owner);
      this.whereIsPlanet = system;
   }

   public void upgrade(){
      level++;
   }

   public void downgrade(){
      level--;
   }
}
