package game.map;

import game.staticData.CommonData;
import game.planet.Planet;
import game.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PremadeGalaxyGenerator {
   public static Set<StarSystem> generateGalaxy(int width, int height) {
      Set<StarSystem> result = new HashSet<>();

      int offsetW = width / 11;
      int offsetH = height / 11;

      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            result.add(new StarSystem(i * offsetW, j * offsetH, id(i, j)));
         }
      }

      for (int i = 0; i < 10; i++) {
         for (int j = 0; j < 10; j++) {
            StarSystem current = findByID(result, id(i, j));
            List<StarSystem> connections = new ArrayList<>();
            if (findByID(result, id(i, j - 1)) != null) connections.add(findByID(result, id(i, j - 1)));
            if (findByID(result, id(i - 1, j)) != null) connections.add(findByID(result, id(i - 1, j)));
            if (findByID(result, id(i + 1, j)) != null) connections.add(findByID(result, id(i + 1, j)));
            if (findByID(result, id(i, j + 1)) != null) connections.add(findByID(result, id(i, j + 1)));

            for (StarSystem connection : connections){
               current.addConnection(connection);
            }
         }
      }

      for (int i = 0; i < 10; i+=3) {
         for (int j = 0; j < 10; j+=3) {
            Planet planet = new Planet(new Player("none", Color.darkGray), findByID(result, id(i,j)));
            CommonData.planets.add(planet);
         }
      }

      return result;
   }

   private static String id(int i, int j) {
      return "" + i + "" + j;
   }

   private static StarSystem findByID(Set<StarSystem> starSystems, String ID) {
      return starSystems.stream().filter(system -> system.ID.equals(ID)).findFirst().orElse(null);
   }
}
