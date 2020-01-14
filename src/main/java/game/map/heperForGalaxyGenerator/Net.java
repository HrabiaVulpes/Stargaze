package game.map.heperForGalaxyGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Net {
    public List<Square> myNet;

    public Net(int size, int shift, int side) {
        myNet = new ArrayList<Square>();
        generateSpiral(size, side, shift);
        //spiralNet(side, shift);
        int[][] seeSpiral = new int[size][size];
//        System.out.println("list of my square");
//        myNet.forEach(square -> {
//
//            System.out.println(myNet.indexOf(square) +" " +"mySize: "+square.side);
//            seeSpiral[square.row - 1][square.column - 1] = myNet.indexOf(square);
//
//
//        });
//        System.out.println();
//        System.out.println();
//        for (int i = 0; i < seeSpiral.length; i++) {
//            for (int j = 0; j < seeSpiral[i].length; j++) {
//                System.out.print(seeSpiral[i][j] + ",");
//            }
//            System.out.println();
//        }

    }

    private void normalNet(int side, int shift) {
        for (int coll = 1; coll < 5; coll++) {
            for (int row = 1; row < 5; row++) {
                myNet.add(new Square(coll, row, side, shift));
            }
        }
    }

//    private void spiralNet(int side, int shift) {
//        myNet.add(new Square(1, 1, side, shift));
//        myNet.add(new Square(2, 1, side, shift));
//        myNet.add(new Square(3, 1, side, shift));
//        myNet.add(new Square(4, 1, side, shift));
//        myNet.add(new Square(4, 2, side, shift));
//        myNet.add(new Square(4, 3, side, shift));
//        myNet.add(new Square(4, 4, side, shift));
//        myNet.add(new Square(3, 4, side, shift));
//        myNet.add(new Square(2, 4, side, shift));
//        myNet.add(new Square(1, 4, side, shift));
//        myNet.add(new Square(1, 3, side, shift));
//        myNet.add(new Square(1, 2, side, shift));
//        myNet.add(new Square(2, 2, side, shift));
//        myNet.add(new Square(3, 2, side, shift));
//        myNet.add(new Square(3, 3, side, shift));
//        myNet.add(new Square(2, 3, side, shift));
//    }

    private void generateSpiral(int size, int side, int shift) {
        spiral(size);
        setShiftAndSide(side, shift);
    }

    private void spiral(int size) {
        int step = 0;
        while (step < size * 2) {
            if (step == 0) goLeft(1, 1, size);
            else goLeft((step / 4) + 1, step / 4 + 1, size - step / 4);
            step++;
            goDown(size - (step / 4), (step / 4) + 1, size - (step / 4));
//            if (step >= size * 2) break;
            step++;
            goRight(size - (step / 4), size - (step / 4), (step / 4) + 1);
            step++;
            goUp((step / 4) + 1, size - (step / 4), (step / 4) + 1);
//            if (step >= size * 2) break;
            step++;
        }
    }


    private void goLeft(int row, int startCol, int endCol) {
        for (int i = startCol; i < endCol; i++) {
            myNet.add(new Square(i, row));
        }
    }

    private void goRight(int row, int startCol, int endCol) {
        for (int i = startCol; i > endCol; i--) {
            myNet.add(new Square(i, row));
        }
    }

    private void goUp(int column, int startRow, int endRow) {
        for (int i = startRow; i > endRow; i--) {
            myNet.add(new Square(column, i));
        }
    }

    private void goDown(int column, int startRow, int endRow) {
        for (int i = startRow; i < endRow; i++) {
            myNet.add(new Square(column, i));
        }
    }


    private void setShiftAndSide(int side, int shift) {
        myNet.forEach(square -> {
            square.setShift(shift);
            square.setSide(side);
            square.setMinMaxValue();
        });
    }

}
