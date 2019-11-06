package game;

import game.player.Player;

import java.util.HashSet;

import static game.CommonData.*;

public class Game {
   public void mainLoop(){
      initData();
      while(continueGame){
         long startTime = System.currentTimeMillis();

         economics();
         diplomacy();
         orders();
         movement();
         warfare();
         development();

         try {
            wait(gameSpeed + startTime - System.currentTimeMillis());
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   private void initData() {
      players = new HashSet<>();
      players.add(new Player("Red"));
      players.add(new Player("Green"));
      players.add(new Player("Blue"));
      players.add(new Player("Black"));
      players.add(new Player("White"));
   }

   private void economics() {
      for (Player player:players){
         player.money += galaxy.stream().filter(system -> system.getOwner().equals(player.name)).count();
         player.money -= ships.stream().filter(ship -> ship.owner.equals(player.name)).count();
         player.money -= fortresses.stream().filter(fortress -> fortress.owner.equals(player.name)).mapToInt(fortress -> fortress.level).sum();
      }
   }

   private void diplomacy() {

   }

   private void orders() {

   }

   private void movement() {

   }

   private void warfare() {

   }

   private void development() {

   }
}
