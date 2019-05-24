package cellular_automata.model.utils;

import javafx.util.Pair;

public class PositionUtils {

    static public int toIndex(double position, double length, int numEl) {
        return (int) (position/(length/numEl));
    }

    static public int toIndex(double position, double size) {
        return (int) (position/(size));
    }

    static public Pair<Integer,Integer> toIndex(double positionX, double positionY, double width, double height, int numElWidth, int numElHeight) {
        int rIndex = (int)(positionY/(height/numElHeight));
        int cIndex = (int)(positionX/(width/numElWidth));
        return new Pair<>(rIndex,cIndex);
    }

    static public Pair<Integer,Integer> toIndex(double positionX, double positionY, double sizeOnHeight, double sizeOnWidth) {
        int rIndex = (int)(positionY/sizeOnHeight);
        int cIndex = (int)(positionX/sizeOnWidth);
        return new Pair<>(rIndex,cIndex);
    }


}
