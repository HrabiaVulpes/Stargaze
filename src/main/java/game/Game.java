package game;

import game.gui.BaseGUI;
import game.map.Fortress;
import game.map.Galaxy;
import game.map.GalaxyGenerator;
import game.map.StarSystem;
import game.player.Player;
import game.player.orders.Order;
import game.player.orders.OrderError;
import game.player.orders.OrderType;
import game.ship.Ship;
import game.ship.ShipTypes;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static game.CommonData.*;

public class Game {
    private static Object LOCK = new Object();
    BaseGUI baseGUI;

    public void mainLoop() {
        initData();
        baseGUI = new BaseGUI();

        Thread t = new Thread(baseGUI);
        t.start();

        while (continueGame) {
            long startTime = System.currentTimeMillis();

            economics();
            diplomacy();
            orders();
            movement();
            warfare();
            development();

            synchronized (LOCK) {
                try {
//                    System.out.println("Waiting");
                    long waitTime = gameSpeed + startTime - System.currentTimeMillis();
                    LOCK.wait(waitTime > 0 ? waitTime : 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            cleanUp();
        }
    }

    private void initData() {
        galaxy = new Galaxy(GalaxyGenerator.generateGalaxy(mapWidth, mapHeight));

        players.add(new Player("Red"));
        players.add(new Player("Green"));
        players.add(new Player("Blue"));
        players.add(new Player("Black"));
        players.add(new Player("White"));

        StarSystem system = galaxy.starSystems.stream().findAny().orElseThrow(NoSuchElementException::new);
        ships.add(new Ship(players.get(0), ShipTypes.CORVETTE, system));
    }

    private void economics() {
        for (Player player : players) {
            player.money += galaxy.starSystems.stream().filter(system -> system.getOwner().equals(player.name)).count();
            player.money -= ships.stream().filter(ship -> ship.owner.equals(player.name)).count();
            player.money -= fortresses.stream().filter(fortress -> fortress.owner.equals(player.name)).mapToInt(fortress -> fortress.level).sum();
        }
    }

    private void diplomacy() {

    }

    private void orders() {

    }

    private void movement() {
        doOrdersByType(OrderType.SHIP_MOVE);
        orders = orders.stream().filter(order -> order.type != OrderType.SHIP_MOVE).collect(Collectors.toList());

//        for (Ship ship : ships) {
//            System.out.println("Ship: " + ship.whereIsShip.getX() + ", " + ship.whereIsShip.getY());
//        }
    }

    private void warfare() {
        doOrdersByType(OrderType.SHIP_TAKE_SYSTEM);
        doOrdersByType(OrderType.SHIP_SHOOT);

        orders = orders.stream()
                .filter(order -> order.type != OrderType.SHIP_SHOOT)
                .filter(order -> order.type != OrderType.SHIP_TAKE_SYSTEM)
                .collect(Collectors.toList());

        ships = ships.stream().filter(ship -> ship.hullPoints > 0).collect(Collectors.toList());
        fortresses.forEach(Fortress::siege);
    }

    private void development() {
        doOrdersByType(OrderType.PLANET_UPGRADE);
        doOrdersByType(OrderType.PLANET_DOWNGRADE);
        doOrdersByType(OrderType.PLANET_BUILD_SHIP);
        doOrdersByType(OrderType.FORTRESS_UPGRADE);
        doOrdersByType(OrderType.FORTRESS_DOWNGRADE);

        orders = orders.stream()
                .filter(order -> order.type != OrderType.PLANET_UPGRADE)
                .filter(order -> order.type != OrderType.PLANET_DOWNGRADE)
                .filter(order -> order.type != OrderType.PLANET_BUILD_SHIP)
                .filter(order -> order.type != OrderType.FORTRESS_UPGRADE)
                .filter(order -> order.type != OrderType.FORTRESS_DOWNGRADE)
                .collect(Collectors.toList());
    }

    private void cleanUp() {
        fortresses.forEach(fortress -> fortress.orderedAlready = false);
        planets.forEach(planet -> planet.orderedAlready = false);
        ships.forEach(ship -> ship.orderedAlready = false);
        orders.addAll(futureOrders);
        futureOrders.clear();
    }

    private void doOrdersByType(OrderType type) {
        if (orders.stream().anyMatch(order -> order.type == type)) {
            System.out.println("Doing " + orders.stream().filter(order -> order.type == type).count() + " " + type + " ");
            orders.stream().filter(order -> order.type == type)
                    .forEach(
                            order -> {
                                try {
                                    order.runOrder();
                                } catch (OrderError orderError) {
                                    System.out.println(orderError.getMessage());
                                }
                            }
                    );
        }
    }
}
