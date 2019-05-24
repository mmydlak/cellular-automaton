package cellular_automata.model.neighbourhood;

import cellular_automata.model.neighbourhood.Neighbourhood;

public class Neighbourhood1D extends Neighbourhood {

    private boolean[] neighbourMap;

    public Neighbourhood1D(int radius) {
        if(radius <= 0) {
            try {
                throw new Exception("Radius have to be a positive number.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.radius = radius;
        createNeighbourMap();
    }

    public void changeRadius(int radius) {
        if(this.radius != radius) {
            this.radius = radius;
            createNeighbourMap();
        }
    }

    public boolean[] getNeighbourMap(){
        return neighbourMap;
    }

    public boolean[] getNeighbourMapSecure(){
        return neighbourMap.clone();
    }

    private void createNeighbourMap(){
        neighbourMap = new boolean[radius*2+1];
        for(int i=0; i<neighbourMap.length; i++) {
            neighbourMap[i] = true;
        }
        //considered cell = not a neighbour
        neighbourMap[radius] = false;
    }







}
