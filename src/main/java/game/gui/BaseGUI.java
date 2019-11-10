package game.gui;

import game.map.GalaxyGenerator;

import javax.swing.*;
import java.awt.*;

public class BaseGUI extends JFrame {
   static final int width = 1000;
   static final int height = 1000;

   public BaseGUI() {
      initUI();
   }

   public static void main(String[] args) {
      EventQueue.invokeLater(() -> {
         JFrame ex = new BaseGUI();
         ex.setVisible(true);
      });
   }

   private void initUI() {
      JPanel drawPanel = new MapPanel(GalaxyGenerator.generateGalaxy(width, height));
      add(drawPanel);

      setSize(width, height);
      setTitle("Stars");
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}