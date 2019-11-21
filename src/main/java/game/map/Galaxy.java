package game.map;

import game.player.Player;
import game.player.orders.OrderError;

import java.util.Set;
import java.util.stream.Collectors;

public class Galaxy {
    public Set<StarSystem> starSystems;

    public Galaxy(Set<StarSystem> starSystems) {
        this.starSystems = starSystems;
        optimize();
    }

    private void optimize() {
        int minX = starSystems.stream().mapToInt(StarSystem::getX).min().orElse(0);
        int minY = starSystems.stream().mapToInt(StarSystem::getY).min().orElse(0);

        starSystems.forEach(starSystem -> {
            starSystem.setX(starSystem.getX() - minX + 15);
            starSystem.setY(starSystem.getY() - minY + 15);
        });
    }

    public Set<StarSystem> getSystemsByOwner(Player player) {
        return starSystems.stream().filter(system -> system.getOwner().equals(player.name)).collect(Collectors.toSet());
    }

    public StarSystem getSystemByID(String ID) throws OrderError {
        return starSystems.stream().filter(system -> system.ID.equals(ID)).findFirst().orElseThrow(() -> new OrderError("System " + ID + " not found!"));
    }

    public StarSystem getSystemByOwnerAndID(Player player, String ID) throws OrderError {
        return starSystems.stream()
                .filter(system -> system.getOwner().equals(player.name))
                .filter(system -> system.ID.equals(ID)).findFirst()
                .orElseThrow(() -> new OrderError("System " + ID + " not found!"));
    }


}
