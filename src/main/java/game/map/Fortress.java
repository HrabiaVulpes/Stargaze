package game.map;

public class Fortress extends MapElement{
   public Integer level = 0;
   public Integer fortifiedLevel = 0;
   public StarSystem whereIsFortress;

   public Fortress(String owner, StarSystem whereIsFortress) {
      super(owner);
      this.whereIsFortress = whereIsFortress;
   }

   public void upgrade(){
      this.level++;
      this.fortifiedLevel++;
   }

   public void downgrade(){
      this.level--;
      this.fortifiedLevel--;
   }

   public void siege(){
      this.fortifiedLevel--;
   }
}
