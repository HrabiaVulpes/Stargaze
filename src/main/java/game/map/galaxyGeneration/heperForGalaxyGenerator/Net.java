package game.map.galaxyGeneration.heperForGalaxyGenerator;

import java.util.ArrayList;
import java.util.List;

public class Net {
    public List<Square> myNet;

    public Net(int shift, int side) {
        myNet = new ArrayList<Square>();
        spiralNet(side, shift);
        System.out.println(myNet.size());
    }

    private void normalNet(int side, int shift) {
        for (int coll = 1; coll < 5; coll++) {
            for (int row = 1; row < 5; row++) {
                myNet.add(new Square(coll, row, side, shift));
            }
        }
    }
    private void spiralNet(int side, int shift) {
        myNet.add(new Square(1,1, side, shift));
        myNet.add(new Square(2,1, side, shift));
        myNet.add(new Square(3,1, side, shift));
        myNet.add(new Square(4,1, side, shift));
        myNet.add(new Square(4,2, side, shift));
        myNet.add(new Square(4,3, side, shift));
        myNet.add(new Square(4,4, side, shift));
        myNet.add(new Square(3,4, side, shift));
        myNet.add(new Square(2,4, side, shift));
        myNet.add(new Square(1,4, side, shift));
        myNet.add(new Square(1,3, side, shift));
        myNet.add(new Square(1,2, side, shift));
        myNet.add(new Square(2,2,side, shift));
        myNet.add(new Square(3,2, side, shift));
        myNet.add(new Square(3,3, side, shift));
        myNet.add(new Square(2,3, side, shift));






    }

    private void goForward(int startColl, int endColl, int row, int side, int shift){
        for(int i = startColl; i < endColl; i++ ){
            myNet.add(new Square(i, row, side, shift));
        }
    }

    private void goBack(int startColl, int endColl, int row, int side, int shift){
        for(int i = startColl; i > endColl; i-- ){
            myNet.add(new Square(i, row, side, shift));
        }
    }

}
