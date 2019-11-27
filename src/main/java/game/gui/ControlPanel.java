package game.gui;

import game.CommonData;
import game.player.orders.OrderMove;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Objects;

import static game.CommonData.mapHeight;

public class ControlPanel extends JPanel implements ActionListener {
    JButton move;

    ControlPanel(){
        setSize(100, mapHeight+50);
        move = new JButton("Move");
        move.setMnemonic(KeyEvent.VK_D);
        move.setActionCommand("move");
        move.addActionListener(this);

        move.setToolTipText("Move randomly");

        add(move);
    }

    public void actionPerformed(ActionEvent e) {
        if ("move".equals(e.getActionCommand())) {
            OrderMove order = new OrderMove(
                    CommonData.players.get(0),
                    CommonData.ships.get(0).ID,
                    Objects.requireNonNull(CommonData.ships.get(0).whereIsShip.getConnections().stream().findAny().orElse(null)).ID
            );

            CommonData.orders.add(order);
        }
        System.out.println("Added");
    }
}
