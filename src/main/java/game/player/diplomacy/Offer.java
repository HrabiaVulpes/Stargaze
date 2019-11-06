package game.player.diplomacy;

import game.ship.Ship;
import game.map.StarSystem;

import java.util.Set;

public class Offer {
   OfferType type;

   DiplomaticStatus offeredDiplomaticStatus;
   Set<StarSystem> offeredStarSystems;
   Set<Ship> offeredShips;
   Integer offeredMoney;
}
