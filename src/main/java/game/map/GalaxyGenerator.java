package game.map;

import java.util.HashSet;
import java.util.Set;

import static game.Utils.numberBetween;

public class GalaxyGenerator {
   public static Set<StarSystem> generateGalaxy(int width, int height) {
      Set<StarSystem> result = new HashSet<>();
      for (int i = 0; i < 1000; i++) {
         result.add(new StarSystem(numberBetween(0, width), numberBetween(0, height)));
      }
      return result;
   }
}
