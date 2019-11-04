package game;

import static game.CommonData.continueGame;
import static game.CommonData.gameSpeed;

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

   }

   private void economics() {

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
