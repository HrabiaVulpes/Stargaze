package game.map;

public class Connection {
    //this is for function description from y = ax + b
    int a;
    int b;
    StarSystem starSystemPrime;
    StarSystem starSystemSecond;

    Connection(StarSystem starSystemPrime, StarSystem starSystemSecond) {
        this.starSystemPrime = starSystemPrime;
        this.starSystemSecond = starSystemSecond;

        this.a = getFunctionA();
        this.b = getFunctionB();
    }

    private int getFunctionA() {
        if (starSystemPrime.getX() == starSystemSecond.getX()) { return 0; } else {
            return (starSystemSecond.getY() - starSystemPrime.getY()) / (starSystemSecond.getX() - starSystemPrime
                    .getX());
        }
    }

    private int getFunctionB() {
        return starSystemPrime.getY() - starSystemPrime.getX() * (starSystemSecond.getY() - starSystemPrime.getY()) / (
                starSystemSecond.getX() - starSystemPrime.getX());
    }

    public Place whereIsSystem(StarSystem testedSystem){
        if(testedSystem.getY() == this.a * testedSystem.getX() + this.b)
            return  Place.ON_CONNECTION;
        else if (testedSystem.getY() > this.a * testedSystem.getX() + this.b)
            return Place.OVER_CONNECTION;
        else return Place.ON_CONNECTION;
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
