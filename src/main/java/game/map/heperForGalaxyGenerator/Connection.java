package game.map.heperForGalaxyGenerator;

import game.map.StarSystem;

public class Connection {
    //this is for function description from y = ax + b
    int a;
    int b;
    boolean vertical;
    public StarSystem starSystemPrime;
    public StarSystem starSystemSecond;

    public Connection(StarSystem starSystemPrime, StarSystem starSystemSecond) {
        this.starSystemPrime = starSystemPrime;
        this.starSystemSecond = starSystemSecond;
    }
}


 /*
 y1 = ax1 + b;
 y2 = ax2 +b
 b = y1 - ax1;
 y2 = ax2 + y1 - ax1;
 y2 = (x2-x1)a + y1

 a = y2-y1 /(x2 - x1)
 b = y1 - x1*(y2-y1 /(x2 - x1))
  */
