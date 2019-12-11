package game.gui;

import game.CommonData;
import game.map.MapElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LeftClickMenu extends JPopupMenu implements ActionListener {
   public LeftClickMenu(List<MapElement> clickedElements) {
      for (MapElement element : clickedElements) {
         JMenuItem anItem;
         anItem = new JMenuItem(element.owner.name + ":" + element.getClass().getSimpleName() + ":" + element.ID);
         anItem.addActionListener(this);
         add(anItem);
      }
   }

   @Override
   public void actionPerformed(ActionEvent actionEvent) {
      String[] elementCoords = actionEvent.getActionCommand().split(":");
      MapElement element = null;

      switch (elementCoords[1]) {
         case "Fortress":
            element = CommonData.fortresses.stream()
                    .filter(fortress -> fortress.ID.equals(elementCoords[2]))
                    .findFirst().orElse(null);
            break;
         case "Planet":
            element = CommonData.planets.stream()
                    .filter(fortress -> fortress.ID.equals(elementCoords[2]))
                    .findFirst().orElse(null);
            break;
         case "Ship":
            element = CommonData.ships.stream()
                    .filter(fortress -> fortress.ID.equals(elementCoords[2]))
                    .findFirst().orElse(null);
            break;
         case "StarSystem":
            element = CommonData.galaxy.starSystems.stream()
                    .filter(fortress -> fortress.ID.equals(elementCoords[2]))
                    .findFirst().orElse(null);
            break;
      }

      CommonData.selected = element;
   }
}
