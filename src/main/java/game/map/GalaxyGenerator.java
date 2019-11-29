package game.map;

import java.util.HashSet;
import java.util.Set;

import static game.Utils.numberBetween;

public class GalaxyGenerator {

    static Set<Connection> connections = new HashSet<>();

    private static int numberBetween(int a, int b) {
        return (int) (Math.random() * (b - a) + a);
    }

    public static Set<StarSystem> generateGalaxy(int width, int height) {
        Set<StarSystem> result = new HashSet<>();
        StarSystem baseSystem = new StarSystem(numberBetween(0, width), numberBetween(0, height));
        StarSystem childSystem = new StarSystem(numberBetween(0, width), numberBetween(0, height));
        for (int i = 0; i < MapSetting.numberOfStars; i++) {
            while (!isStarSystemClose(baseSystem, childSystem, MapSetting.maxLengthOfConnection) ||
                   isConnectionCross(childSystem, baseSystem)){
//                System.out.println(isConnectionCross(childSystem, baseSystem));
                childSystem = new StarSystem(numberBetween(0, width), numberBetween(0, height));
            }
            connections.add(new Connection(baseSystem, childSystem));
            baseSystem.addConnection(childSystem);
            result.add(baseSystem);
            baseSystem = childSystem;
        }

        result.add(childSystem);
        //connectTwoNearestStarSystem(result);
        return result;
    }

    static boolean isStarSystemClose(StarSystem s1, StarSystem s2, int distance) {
        return distance >= (s1.getX() - s2.getX()) * (s1.getX() - s2.getX()) + (s1.getY() - s2.getY()) * (s1.getY() - s2
                .getY()) &&
               s1 != s2;
    }

    static void connectTwoNearestStarSystem(Set<StarSystem> starSystems) {

        StarSystem nearestStarSystem = null;
        for (StarSystem s1 : starSystems) {
            int distance = MapSetting.closeConnection;
            for (StarSystem s2 : starSystems) {
                if (getDistance(s1, s2) < distance && s1 != s2) {
                    distance = getDistance(s1, s2);
                    nearestStarSystem = s2;

                }
            }
            if (nearestStarSystem != null)
            { s1.addConnection(nearestStarSystem);
            }
        }

    }

    static void createCloseConnection(Set<StarSystem> starSystems, int distance) {
        starSystems.forEach(s1 -> {
            starSystems.forEach(s2 -> {
                if (isStarSystemClose(s1, s2, distance)) { s2.addConnection(s1); }
            });
        });
    }

    static int getDistance(StarSystem s1, StarSystem s2) {
        if (s1 == s2) { return 0; }
        int a = Math.abs(s1.getX() - s2.getX()) * Math.abs(s1.getX() - s2.getX())
                + Math.abs(s1.getY() - s2.getY()) * Math.abs(s1.getY() - s2.getY());
        return a;
    }

    static boolean isConnectionCross(StarSystem s1, StarSystem s2) {
        int a = 0;
        for (Connection connection : connections) {
            a++;
            System.out.println(a);
            if(ConnectionCalculator.doIntersect(s1,s2,connection.starSystemPrime, connection.starSystemSecond))
                return true;
        }
        return false;
    }
}