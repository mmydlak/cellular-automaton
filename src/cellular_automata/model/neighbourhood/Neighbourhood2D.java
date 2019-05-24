package cellular_automata.model.neighbourhood;

import cellular_automata.model.neighbourhood.Neighbourhood;

public class Neighbourhood2D extends Neighbourhood {

    private int thick;
    private boolean[][] neighbourMap;
    private int neighboursNumber;

    public Neighbourhood2D(int radius, int thick) throws Exception {
        if(radius <= 0) {
            throw new Exception("Radius have to be a positive number.");
        }
        if(thick < 0) {
            throw new Exception("Thickness have to be a positive number or 0.");
        }
        else if (thick > radius){
            throw new Exception("Thickness cannot be greater than radius.");
        }
        this.radius = radius;
        this.thick = thick;
        createNeighbourMap();
        calculateNeighboursNumber();
    }

    public Neighbourhood2D(int radius) throws Exception {
        this(radius, radius);
    }

    public Neighbourhood2D(boolean[][] neighbourMap) {
        if(neighbourMap.length != neighbourMap[0].length) {
            try {
                throw new Exception("Only square neighbourhood array is accepted.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.neighbourMap = neighbourMap;
        countNeighbours();
    }

    public boolean[][] getNeighbourMap(){
        return neighbourMap;
    }

    public boolean[][] getNeighbourMapSecure(){
        boolean[][] copy = new boolean[neighbourMap.length][];
        for (int i = 0; i < neighbourMap.length; i++) {
            copy[i] = new boolean[neighbourMap[i].length];
            System.arraycopy(neighbourMap[i], 0, copy[i], 0, copy[i].length);
        }
        return copy;
    }

    public int getNeighboursNumber(){
        return neighboursNumber;
    }

    private void createNeighbourMap(){
        neighbourMap = new boolean[radius*2+1][radius*2+1];
        for(int i=0; i<neighbourMap.length; i++) {
            for(int j=0; j<neighbourMap.length; j++) {
                if((i>=radius-thick && i<=radius+thick) && (j>=radius-thick && j<=radius+thick)) {
                    neighbourMap[i][j] = true;
                }
                else {
                    neighbourMap[i][j] = false;
                }
            }
        }
        //considered cell = not a neighbour
        neighbourMap[radius][radius] = false;
    }

    private void calculateNeighboursNumber() {
        neighboursNumber = 2*(2*thick+1)*(2*radius+1) - (2*thick+1)*(2*thick+1) - 1;
    }

    private void countNeighbours(){
        neighboursNumber = 0;
        for(int i=0; i<neighbourMap.length; i++) {
            for(int j=0; j<neighbourMap.length; j++) {
                if(neighbourMap[i][j]) {
                    neighboursNumber++;
                }
            }
        }
    }

}
