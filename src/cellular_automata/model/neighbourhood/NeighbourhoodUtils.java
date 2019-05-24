package cellular_automata.model.neighbourhood;

import cellular_automata.model.neighbourhood.Neighbourhood2D;

public class NeighbourhoodUtils {

    static public Neighbourhood2D union(Neighbourhood2D n1, Neighbourhood2D n2){
        int r1 = n1.getRadius();
        int r2 = n2.getRadius();
        boolean[][] resultMap;
        if(r1 >= r2) {
            resultMap = n1.getNeighbourMapSecure();
            for(int i=0; i<r2; i++) {
                for(int j=0; j<r2; j++) {
                   resultMap[(r1-r2)+i][(r1-r2)+j] |= n2.getNeighbourMap()[i][j];
                }
            }
        }
        else {
            resultMap = n2.getNeighbourMapSecure();
            for(int i=0; i<r1; i++) {
                for(int j=0; j<r1; j++) {
                    resultMap[(r2-r1)+i][(r2-r1)+j] |= n1.getNeighbourMap()[i][j];
                }
            }
        }

        return new Neighbourhood2D(resultMap);
    }

    static public Neighbourhood2D intersection(Neighbourhood2D n1, Neighbourhood2D n2){
        int r1 = n1.getRadius();
        int r2 = n2.getRadius();
        boolean[][] resultMap;
        if(r1 <= r2) {
            resultMap = n1.getNeighbourMapSecure();
            for(int i=0; i<r1; i++) {
                for(int j=0; j<r1; j++) {
                    resultMap[i][j] &= n2.getNeighbourMap()[(r1-r2)+i][(r1-r2)+j];
                }
            }
        }
        else {
            resultMap = n2.getNeighbourMapSecure();
            for(int i=0; i<r2; i++) {
                for(int j=0; j<r2; j++) {
                    resultMap[i][j] &= n1.getNeighbourMap()[(r1-r2)+i][(r1-r2)+j];
                }
            }
        }

       return new Neighbourhood2D(resultMap);
    }

    static public Neighbourhood2D complement(Neighbourhood2D n1, Neighbourhood2D n2) throws Exception{
        int r1 = n1.getRadius();
        int r2 = n2.getRadius();
        if(r1<r2) {
            throw new Exception("Radius of the first Neighbourhood should be greater or equal than radius of the second Neighbourhood.");
        }
        boolean[][] resultMap = n1.getNeighbourMapSecure();
        for(int i=0; i<r2; i++) {
            for(int j=0; j<r2; j++) {
                    resultMap[(r1-r2)+i][(r1-r2)+j] &= !n2.getNeighbourMap()[i][j];
            }
        }
        return new Neighbourhood2D(resultMap);
    }

}
