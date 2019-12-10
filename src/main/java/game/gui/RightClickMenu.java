package game.gui;

import game.map.MapElement;

import javax.swing.*;
import java.util.List;

public class RightClickMenu extends JPopupMenu {
   JMenuItem anItem;

   public RightClickMenu(List<MapElement> clickedElements) {
      anItem = new JMenuItem("Click Me!");
      add(anItem);
   }
}
