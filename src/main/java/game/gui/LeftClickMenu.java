package game.gui;

import game.map.MapElement;

import javax.swing.*;
import java.util.List;

public class LeftClickMenu extends JPopupMenu {
   JMenuItem anItem;

   public LeftClickMenu(List<MapElement> clickedElements) {
      anItem = new JMenuItem("Click Me!");
      add(anItem);
   }
}
