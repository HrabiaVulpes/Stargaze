package game.player;

import game.map.Fortress;
import game.map.Planet;
import game.map.Ship;
import game.map.System;

import java.util.Set;

public class Player {
   public String name;
   public Set<System> systems;
   public Set<Planet> planets;
   public Set<Fortress> fortresses;
   public Set<Ship> ships;

   public Integer money = 0;

   public Player(String name) {
      this.name = name;
   }

   public Player(String name, Set<System> systems, Set<Planet> planets) {
      this.name = name;
      this.systems = systems;
      this.planets = planets;
   }
}
