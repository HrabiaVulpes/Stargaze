package game.player.diplomacy;

import game.map.Ship;
import game.map.System;

import java.util.Set;

public class Offer {
   OfferType type;

   DiplomaticStatus offeredDiplomaticStatus;
   Set<System> offeredSystems;
   Set<Ship> offeredShips;
   Integer offeredMoney;
}
