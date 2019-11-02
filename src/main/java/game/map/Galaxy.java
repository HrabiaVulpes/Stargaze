package game.map;

import java.util.HashSet;
import java.util.Set;

public class Galaxy {
    private static int numberBetween(int a, int b) {
        return (int) (Math.random() * (b - a) + a);
    }

    public static Set<System> generateGalaxy(int width, int height) {
        Set<System> result = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            result.add(new System(numberBetween(0, width), numberBetween(0, height)));
        }
        return result;
    }
}
