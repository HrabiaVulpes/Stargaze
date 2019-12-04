package game.map;

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

      return result;
   }

   private static String id(int i, int j) {
      return "" + i + "" + j;
   }

   private static StarSystem findByID(Set<StarSystem> starSystems, String ID) {
      return starSystems.stream().filter(system -> system.ID.equals(ID)).findFirst().orElse(null);
   }
}
