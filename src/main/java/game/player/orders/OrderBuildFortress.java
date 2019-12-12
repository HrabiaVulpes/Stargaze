package game.player.orders;

import game.CommonData;
import game.map.Fortress;
import game.map.StarSystem;
import game.player.Player;

import java.util.Collections;

import static game.CommonData.getSystemByID;

public class OrderBuildFortress extends Order {
   private String systemID;

   public OrderBuildFortress(Player owner, String systemID) {
      super(owner, OrderType.FORTRESS_BUILD);
      this.systemID = systemID;
      connectedMapElements = Collections.singletonList(CommonData.galaxy.starSystems.stream().filter(system -> system.ID.equals(systemID)).findFirst().orElse(null));
   }

   @Override
   public void runOrder() throws OrderError {
      StarSystem fortressSystem = getSystemByID(systemID);

      if (CommonData.fortresses.stream().noneMatch(fortress -> fortress.whereIsFortress == fortressSystem)){
         CommonData.fortresses.add(new Fortress(CommonData.players.get(0), fortressSystem));
      }
   }
}