package game.map.galaxyGeneration;

import game.map.StarSystem;
import game.map.galaxyGeneration.heperForGalaxyGenerator.Connection;
import game.map.galaxyGeneration.heperForGalaxyGenerator.Net;
import game.staticData.MapSetting;

import java.util.HashSet;
import java.util.Set;

public class GalaxyGenerator {

    static Set<Connection> connections = new HashSet<>();

    public static Set<StarSystem> generateGalaxy(int width, int height) {
        Set<StarSystem> result = new HashSet<>();
        Net net = new Net(0, 100);
        StarSystem base = net.myNet.get(0).randomSystemInSquare();
        StarSystem child = net.myNet.get(1).randomSystemInSquare();
        for (int i = 1; i < net.myNet.size() - 2; i++) {
            while (isConnectionCross(child, base)) {
                child = net.myNet.get(i + 1).randomSystemInSquare();
                System.out.println("a");
            }
            base.addConnection(child);
            child.addConnection(base);
            connections.add(new Connection(base, child));
            result.add(base);
            base = child;
            child = net.myNet.get(i + 2).randomSystemInSquare();


        }
        //        StarSystem baseSystem = new StarSystem(numberBetween(0, width), numberBetween(0, height));
        //        StarSystem childSystem = new StarSystem(numberBetween(0, width), numberBetween(0, height));
        //        for (int i = 0; i < MapSetting.numberOfStars; i++) {
        //            while (!isStarSystemClose(baseSystem, childSystem, MapSetting.maxLengthOfConnection) || !isStarSystemTooClose(baseSystem, childSystem, MapSetting.tooCloseConnection)||
        //                   isConnectionCross(childSystem, baseSystem)) {
        //                System.out.println("problem");
        //                childSystem = new StarSystem(numberBetween(0, width), numberBetween(0, height));
        //            }
        //            connections.add(new Connection(baseSystem, childSystem));
        //            baseSystem.addConnection(childSystem);
        //            result.add(baseSystem);
        //            baseSystem = childSystem;
        //            System.out.println("a");
        //        }
        //
        //        result.add(childSystem);
        //        //createCloseConnection(result, MapSetting.closeConnection);
        //connectTwoNearestStarSystem(result);
        createCloseConnection(result, MapSetting.closeConnection);
        return result;
    }

    static boolean isStarSystemClose(StarSystem s1, StarSystem s2, int distance) {
        return distance >= (s1.getX() - s2.getX()) * (s1.getX() - s2.getX()) + (s1.getY() - s2.getY()) * (s1.getY() - s2
                .getY()) &&
               s1 != s2;
    }

    static boolean isStarSystemTooClose(StarSystem s1, StarSystem s2, int distance) {
        return distance < (s1.getX() - s2.getX()) * (s1.getX() - s2.getX()) + (s1.getY() - s2.getY()) * (s1.getY() - s2
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
            if (nearestStarSystem != null) {
                s1.addConnection(nearestStarSystem);
            }
        }

    }

    static void createCloseConnection(Set<StarSystem> starSystems, int distance) {
        starSystems.forEach(s1 -> {
            starSystems.forEach(s2 -> {
                if (isStarSystemClose(s1, s2, distance) && !isConnectionCross(s1, s2) && Math.random() > 0.7 ) {
                    s2.addConnection(s1);
                    s1.addConnection(s2);
                    connections.add(new Connection(s1, s2));
                }
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
        for (Connection connection : connections) {
            if (ConnectionCalculator.doIntersect(s1, s2, connection.starSystemPrime, connection.starSystemSecond)) {
                return true;
            }
        }
        return false;
    }
}