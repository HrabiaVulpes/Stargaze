package game.map;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class System {
    private int x;
    private int y;
    private Set<System> connections;

    public System(int x, int y) {
        this.x = x;
        this.y = y;
        connections = Collections.emptySet();
    }

    public Set<System> getConnections() {
        return connections;
    }

    public void setConnections(Set<System> connections) {
        this.connections = connections;
    }

    public void addConnection(System connection) {
        if (this.connections == null)
            this.connections = new HashSet<System>();

        this.connections.add(connection);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
