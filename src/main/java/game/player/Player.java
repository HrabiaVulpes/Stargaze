package game.player;

import game.map.Fortress;
import game.map.Planet;
import game.map.Ship;
import game.map.System;

import java.util.Set;

public class Player {
   String name;
   Set<System> systems;
   Set<Planet> planets;
   Set<Fortress> fortresses;
   Set<Ship> ships;
}