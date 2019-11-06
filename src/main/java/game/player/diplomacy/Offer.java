package game.player.diplomacy;

import game.map.StarSystem;
import game.ship.Ship;

import java.util.Set;

public class Offer {
   OfferType type;

   DiplomaticStatus offeredDiplomaticStatus;
   Set<StarSystem> offeredStarSystems;
   Set<Ship> offeredShips;
   Integer offeredMoney;
}
