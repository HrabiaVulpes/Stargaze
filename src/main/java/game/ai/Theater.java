package game.ai;

import game.map.StarSystem;
import game.player.Player;
import game.ship.Ship;

import java.util.List;

public class Theater {
   Player playerTheater;

   List<StarSystem> borderSystems;
   List<StarSystem> subBorderSystems;

   List<Ship> assignedFleet;

   TheaterStance stance;
}
