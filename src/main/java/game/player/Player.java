package game.player;

import game.staticData.CommonData;

import java.awt.*;

public class Player {
    public String name;
    public Color color;

    public long money = 0;
    public int offencesTech = 0;
    public int defencesTech = 0;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public long getIncome() {
        long in = CommonData.galaxy.starSystems.stream().filter(starSystem -> starSystem.getOwner().equals(name)).count();
        long out = 0;
        out += CommonData.ships.stream().filter(ship -> ship.owner.equals(name)).count();
        out += CommonData.fortresses.stream().filter(fortress -> fortress.owner.equals(name)).mapToInt(a -> a.level).sum();

        return in - out;
    }

    public long getEmpireSize() {
        return CommonData.galaxy.starSystems.stream().filter(starSystem -> starSystem.getOwner().equals(name)).count();
    }

    public long getShipCount(){
        return CommonData.ships.stream().filter(ship -> ship.owner.equals(name)).count();
    }

    public long getPlanetCount(){
        return CommonData.planets.stream().filter(planet -> planet.owner.equals(name)).count();
    }
}
